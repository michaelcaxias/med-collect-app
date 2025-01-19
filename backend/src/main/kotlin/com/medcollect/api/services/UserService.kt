package com.medcollect.api.services

import com.medcollect.api.dtos.CreateUserRequest
import com.medcollect.api.models.Role
import com.medcollect.api.models.User
import com.medcollect.api.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class UserService(

    private val userRepository: UserRepository,
    private val firebaseAuthService: FirebaseAuthService
) {
    companion object {
        private const val ROLE_CLAIM = "roles"
    }

    fun createUser(request: CreateUserRequest): User {
        if (userRepository.findByFirebaseUid(request.firebaseUid) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
        }

        val firebaseUser = firebaseAuthService.getUser(request.firebaseUid)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        val roles = firebaseUser.customClaims?.get(ROLE_CLAIM)?.let { roles ->
            when (roles) {
                is String -> roles.split(",")
                    .map { Role.valueOf(it) }
                    .toSet()
                else -> setOf(Role.PATIENT)
            }
        } ?: setOf(Role.PATIENT)

        return userRepository.save(
            User(
                name = request.name,
                email = firebaseUser.email,
                roles = roles,
                firebaseUid = request.firebaseUid,
                createdAt = LocalDateTime.now()
            )
        )
    }

    fun getUserByFirebaseUid(firebaseUid: String): User? =
        userRepository.findByFirebaseUid(firebaseUid)
}
