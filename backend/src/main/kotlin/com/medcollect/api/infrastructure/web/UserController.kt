package com.medcollect.api.infrastructure.web

import com.medcollect.api.domain.models.User
import com.medcollect.api.domain.ports.`in`.CreateUserCommand
import com.medcollect.api.domain.ports.`in`.CreateUserInputPort
import com.medcollect.api.domain.ports.`in`.GetUserInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val createUserInputPort: CreateUserInputPort,
    private val getUserInputPort: GetUserInputPort
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody request: CreateUserRequest): User {
        val command = CreateUserCommand(
            name = request.name,
            firebaseUid = request.firebaseUid
        )
        return createUserInputPort.execute(command)
    }

    @GetMapping("/firebase/{firebaseUid}")
    fun getUserByFirebaseUid(@PathVariable firebaseUid: String): User? =
        getUserInputPort.getUserByFirebaseUid(firebaseUid)
}

data class CreateUserRequest(
    val name: String,
    val firebaseUid: String
)
