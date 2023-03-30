package com.pico.make_it_so.data.firebase.services.authentication

interface AccountService {

    suspend fun linkAccount(email: String, password: String)
}