package com.medcollect.api.domain.ports.`in`

import com.medcollect.api.domain.models.User

interface GetUserInputPort {
    fun getUserByFirebaseUid(firebaseUid: String): User?
}
