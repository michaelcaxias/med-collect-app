package com.medcollect.api.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.medcollect.api.models.User;
import com.medcollect.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FirebaseAuthService {
    private final FirebaseAuth firebaseAuth;
    private final UserRepository userRepository;

    public Optional<User> verifyAndGetUser(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            return userRepository.findByFirebaseUid(decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            return Optional.empty();
        }
    }

    public User createUser(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
        
        return userRepository.save(User.builder()
                .name(decodedToken.getName())
                .email(decodedToken.getEmail())
                .firebaseUid(decodedToken.getUid())
                .roles(Set.of("USER"))
                .build());
    }

    public void deleteUser(String firebaseUid) throws FirebaseAuthException {
        firebaseAuth.deleteUser(firebaseUid);
        userRepository.findByFirebaseUid(firebaseUid)
                .ifPresent(userRepository::delete);
    }

    public boolean isEmailVerified(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
        return Boolean.TRUE.equals(decodedToken.getClaims().get("email_verified"));
    }
}
