package com.medcollect.api.infrastructure.persistence

import com.medcollect.api.infrastructure.persistence.entities.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<UserEntity, String> {
    fun findByFirebaseUid(firebaseUid: String): UserEntity?
}
