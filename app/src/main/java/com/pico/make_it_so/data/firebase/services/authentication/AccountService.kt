package com.pico.make_it_so.data.firebase.services.authentication

import com.google.firebase.auth.FirebaseUser

interface AccountService {

    val currentUser: FirebaseUser?
    suspend fun linkAccount(email: String, password: String)
    suspend fun createAnonymousAccount()

    suspend fun login(email: String, password: String)
    suspend fun signUp(email: String, password: String)
}