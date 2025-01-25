package com.medcollect.api.application.usecases

import com.medcollect.api.domain.models.Role
import com.medcollect.api.domain.models.User
import com.medcollect.api.domain.ports.`in`.CreateUserInputPort
import com.medcollect.api.domain.ports.`in`.CreateUserCommand
import com.medcollect.api.domain.ports.out.FirebaseAuthPort
import com.medcollect.api.domain.ports.out.UserPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class CreateUserUseCase(
    private val userPort: UserPort,
    private val firebaseAuthPort: FirebaseAuthPort
) : CreateUserInputPort {
    companion object {
        private const val ROLE_CLAIM = "roles"
    }

    override fun execute(request: CreateUserCommand): User {
        if (userPort.findByFirebaseUid(request.firebaseUid) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
        }

        val firebaseUser = firebaseAuthPort.getUser(request.firebaseUid)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        val roles = getFirebaseUserRoles(firebaseUser.customClaims)

        return userPort.save(
            User(
                name = request.name,
                email = firebaseUser.email,
                roles = roles,
                firebaseUid = request.firebaseUid,
                createdAt = LocalDateTime.now()
            )
        )
    }

    private fun getFirebaseUserRoles(customClaims: Map<String, Any>?): Set<Role> {
        val patientSet = setOf(Role.PATIENT)

        return customClaims?.get(ROLE_CLAIM)?.let { roles ->
            when (roles) {
                is String -> roles.split(",")
                    .map { Role.valueOf(it) }
                    .toSet()
                else -> patientSet
            }
        } ?: patientSet
    }
}
