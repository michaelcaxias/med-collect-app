package com.medcollect.api.infrastructure.adapters

import com.google.firebase.auth.FirebaseAuth
import com.medcollect.api.domain.models.FirebaseUser
import com.medcollect.api.domain.ports.FirebaseAuthPort
import org.springframework.stereotype.Component

@Component
class FirebaseAuthAdapter : FirebaseAuthPort {
    override fun getUser(firebaseUid: String): FirebaseUser? {
        return try {
            val user = FirebaseAuth.getInstance().getUser(firebaseUid)
            FirebaseUser(
                email = user.email,
                customClaims = user.customClaims
            )
        } catch (e: Exception) {
            null
        }
    }
}
