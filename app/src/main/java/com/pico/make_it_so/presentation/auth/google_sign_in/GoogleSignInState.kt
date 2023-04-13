package com.pico.make_it_so.presentation.auth.google_sign_in

data class GoogleSignInState(
    val isSignInSuccessful: Boolean = false,
    val errorMessage: String? = null
)
