package com.medcollect.api.domain.ports

import com.medcollect.api.domain.models.User
import java.util.*

interface UserPort {
    fun save(user: User): User
    fun findByFirebaseUid(firebaseUid: String): User?
}
