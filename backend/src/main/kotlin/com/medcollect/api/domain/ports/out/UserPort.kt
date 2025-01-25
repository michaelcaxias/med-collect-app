package com.medcollect.api.domain.ports.out

import com.medcollect.api.domain.models.User

interface UserPort {
    fun save(user: User): User
    fun findByFirebaseUid(firebaseUid: String): User?
}
