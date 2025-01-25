package com.medcollect.api.domain.ports.`in`

import com.medcollect.api.domain.models.User

interface CreateUserInputPort {
    fun execute(request: CreateUserCommand): User
}

data class CreateUserCommand(
    val name: String,
    val firebaseUid: String
)
