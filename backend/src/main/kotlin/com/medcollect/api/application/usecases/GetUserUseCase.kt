package com.medcollect.api.application.usecases

import com.medcollect.api.domain.models.User
import com.medcollect.api.domain.ports.`in`.GetUserInputPort
import com.medcollect.api.domain.ports.out.UserPort
import org.springframework.stereotype.Service

@Service
class GetUserUseCase(
    private val userPort: UserPort
) : GetUserInputPort {
    override fun getUserByFirebaseUid(firebaseUid: String): User? {
        return userPort.findByFirebaseUid(firebaseUid)
    }
}
