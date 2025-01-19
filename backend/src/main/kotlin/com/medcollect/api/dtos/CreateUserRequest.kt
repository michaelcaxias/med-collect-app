package com.medcollect.api.dtos

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @field:NotBlank
    val firebaseUid: String,
    
    @field:NotBlank
    val name: String
)
