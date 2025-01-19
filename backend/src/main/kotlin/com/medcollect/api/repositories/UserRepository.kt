package com.medcollect.api.repositories

import com.medcollect.api.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByFirebaseUid(firebaseUid: String): User?
}
