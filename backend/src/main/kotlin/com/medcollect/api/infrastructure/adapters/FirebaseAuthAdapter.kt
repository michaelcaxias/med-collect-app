package com.medcollect.api.infrastructure.adapters

import com.google.firebase.auth.FirebaseAuth
import com.medcollect.api.domain.models.FirebaseUser
import com.medcollect.api.domain.models.Role
import com.medcollect.api.domain.ports.out.FirebaseAuthPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FirebaseAuthAdapter(
    @Autowired private val firebaseAuth: FirebaseAuth
) : FirebaseAuthPort {
    companion object {
        private const val ROLE_CLAIM = "roles"
    }

    override fun getUser(firebaseUid: String): FirebaseUser? {
        return try {
            val user = firebaseAuth.getUser(firebaseUid)
            FirebaseUser(
                email = user.email,
                customClaims = user.customClaims
            )
        } catch (e: Exception) {
            null
        }
    }

    override fun getUserRoles(firebaseUid: String): Set<Role> {
        val user = getUser(firebaseUid) ?: return setOf(Role.PATIENT)
        return extractRolesFromClaims(user.customClaims)
    }

    private fun extractRolesFromClaims(claims: Map<String, Any>?): Set<Role> {
        val defaultRoles = setOf(Role.PATIENT)
        return claims?.get(ROLE_CLAIM)?.let { roles ->
            when (roles) {
                is String -> roles.split(",")
                    .map { Role.valueOf(it) }
                    .toSet()
                else -> defaultRoles
            }
        } ?: defaultRoles
    }
}
