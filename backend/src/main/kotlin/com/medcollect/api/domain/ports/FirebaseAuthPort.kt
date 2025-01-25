package com.medcollect.api.domain.ports

import com.medcollect.api.domain.models.FirebaseUser

interface FirebaseAuthPort {
    fun getUser(firebaseUid: String): FirebaseUser?
}
