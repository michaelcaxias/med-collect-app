package com.medcollect.api.infrastructure.persistence

import com.medcollect.api.infrastructure.persistence.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByFirebaseUid(firebaseUid: String): UserEntity?
}
