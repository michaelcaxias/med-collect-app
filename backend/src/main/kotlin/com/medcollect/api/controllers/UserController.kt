package com.medcollect.api.controllers

import com.medcollect.api.dtos.CreateUserRequest
import com.medcollect.api.models.User
import com.medcollect.api.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid request: CreateUserRequest): User =
        userService.createUser(request)

    @GetMapping("/firebase/{firebaseUid}")
    fun getUserByFirebaseUid(@PathVariable firebaseUid: String): User? =
        userService.getUserByFirebaseUid(firebaseUid)
}
