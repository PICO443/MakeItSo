package com.pico.make_it_so.presentation.home.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.model.User
import com.pico.make_it_so.presentation.auth.google_sign_in.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor (private val googleAuthUiClient: GoogleAuthUiClient, private val accountService: AccountService): ViewModel() {
    var uiState by mutableStateOf(SettingsUiState())
        private set

    init {
        uiState = uiState.copy(user = accountService.currentUser?.run {
            User(
                username = displayName.orEmpty(),
                email = email.orEmpty(),
                profilePicUrl = photoUrl?.toString().orEmpty(),
            )
        },
            isLoggedIn = accountService.currentUser?.isAnonymous?.not() ?: false
        )
    }

    suspend fun signOut(){
        googleAuthUiClient.signOut()
    }
}