package com.medcollect.api.infrastructure.persistence.entities

import com.medcollect.api.domain.models.Role
import com.medcollect.api.domain.models.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    
    @Column(nullable = false)
    val name: String,
    
    @Column(nullable = false)
    val email: String,
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    val roles: Set<Role>,
    
    @Column(unique = true, nullable = false)
    val firebaseUid: String,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime
) {
    fun toDomain() = User(
        id = id,
        name = name,
        email = email,
        roles = roles,
        firebaseUid = firebaseUid,
        createdAt = createdAt
    )

    companion object {
        fun fromDomain(user: User) = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            roles = user.roles,
            firebaseUid = user.firebaseUid,
            createdAt = user.createdAt
        )
    }
}
