package com.medcollect.api.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
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

    public Optional<UserRecord> getUser(String firebaseUid) {
        try {
            return Optional.of(firebaseAuth.getUser(firebaseUid));
        } catch (FirebaseAuthException e) {
            return Optional.empty();
        }
    }
}
