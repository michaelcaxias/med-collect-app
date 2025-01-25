package com.medcollect.api.domain.models

data class FirebaseUser(
    val email: String,
    val customClaims: Map<String, Any>?
)
