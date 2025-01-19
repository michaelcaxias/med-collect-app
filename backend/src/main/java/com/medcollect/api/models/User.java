package com.medcollect.api.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Set;

@Builder
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_firebase_uid", columnList = "firebaseUid", unique = true)
})
public record User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id,

    @Column(nullable = false)
    String name,

    @Column(nullable = false, unique = true)
    String email,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    Set<String> roles,

    @Column(nullable = false)
    String firebaseUid
) {
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
