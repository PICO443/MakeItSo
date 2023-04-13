package com.pico.make_it_so.presentation.auth.google_sign_in

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val name: String?,
    val profilePictureUrl: String?
)
