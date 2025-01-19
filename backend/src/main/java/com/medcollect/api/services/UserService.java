package com.medcollect.api.services;

import com.medcollect.api.dtos.CreateUserRequest;
import com.medcollect.api.models.Role;
import com.medcollect.api.models.User;
import com.medcollect.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FirebaseAuthService firebaseAuthService;

    private static final String ROLE_CLAIM = "roles";

    public User createUser(CreateUserRequest request) {
        if (userRepository.findByFirebaseUid(request.firebaseUid()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        final var firebaseUser = firebaseAuthService.getUser(request.firebaseUid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        final var baseUser = User.builder()
                .firebaseUid(request.firebaseUid())
                .name(request.name())
                .email(firebaseUser.getEmail());

        final var claims = firebaseUser.getCustomClaims();

        if (Objects.nonNull(claims) && claims.containsKey(ROLE_CLAIM)) {
            final var roles = claims.get(ROLE_CLAIM);

            if (roles instanceof String role) {
                final List<String> splitRoles = List.of(role.split(","));
                final Set<Role> rolesSet = splitRoles.stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());

                return userRepository.save(baseUser.roles(rolesSet).build());
            }
        }

        return userRepository.save(baseUser.roles(Set.of(Role.PATIENT)).build());
    }

    public Optional<User> getUserByFirebaseUid(String firebaseUid) {

        return userRepository.findByFirebaseUid(firebaseUid);
    }
}
