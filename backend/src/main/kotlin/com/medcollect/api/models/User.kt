package com.medcollect.api.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "users",
    indexes = [Index(name = "idx_firebase_uid", columnList = "firebaseUid", unique = true)]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Column(name = "role")
    val roles: Set<Role>,

    @Column(nullable = false)
    val firebaseUid: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime
) {
    fun hasRole(role: String): Boolean = roles.contains(Role.valueOf(role))
}
