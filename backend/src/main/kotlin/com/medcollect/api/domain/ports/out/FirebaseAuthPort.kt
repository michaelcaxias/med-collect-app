package com.medcollect.api.domain.ports.out

import com.medcollect.api.domain.models.FirebaseUser
import com.medcollect.api.domain.models.Role

interface FirebaseAuthPort {
    fun getUser(firebaseUid: String): FirebaseUser?
    fun getUserRoles(firebaseUid: String): Set<Role>
}
