package com.medcollect.api.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService(
    private val firebaseAuth: FirebaseAuth
) {
    fun getUser(firebaseUid: String): UserRecord? = try {
        firebaseAuth.getUser(firebaseUid)
    } catch (e: FirebaseAuthException) {
        null
    }
}
