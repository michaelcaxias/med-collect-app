package com.medcollect.api.infrastructure.adapters

import com.medcollect.api.domain.models.User
import com.medcollect.api.domain.ports.UserPort
import com.medcollect.api.infrastructure.persistence.UserRepository
import com.medcollect.api.infrastructure.persistence.entities.UserEntity
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val userRepository: UserRepository
) : UserPort {
    override fun save(user: User): User {
        val userEntity = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            roles = user.roles,
            firebaseUid = user.firebaseUid,
            createdAt = user.createdAt
        )
        return userRepository.save(userEntity).toDomain()
    }

    override fun findByFirebaseUid(firebaseUid: String): User? {
        return userRepository.findByFirebaseUid(firebaseUid)?.toDomain()
    }
}
