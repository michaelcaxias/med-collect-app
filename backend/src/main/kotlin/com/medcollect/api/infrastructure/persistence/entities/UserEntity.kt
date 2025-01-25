package com.medcollect.api.infrastructure.persistence.entities

import com.medcollect.api.domain.models.Role
import com.medcollect.api.domain.models.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "users")
data class UserEntity(
    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val roles: Set<Role>,
    val firebaseUid: String,
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
}
