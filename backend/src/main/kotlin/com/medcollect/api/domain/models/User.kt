package com.medcollect.api.domain.models

import java.time.LocalDateTime

data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val roles: Set<Role>,
    val firebaseUid: String,
    val createdAt: LocalDateTime
)
