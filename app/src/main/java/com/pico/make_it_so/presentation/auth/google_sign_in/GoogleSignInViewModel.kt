package com.pico.make_it_so.presentation.auth.google_sign_in

import android.content.Intent
import android.content.IntentSender
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    var uiState by mutableStateOf(GoogleSignInState())
        private set

    suspend fun onSignInResult(resultIntent: Intent?) {
        resultIntent?.let {
            val signInResult = googleAuthUiClient.signInWithIntent(it)
            uiState = uiState.copy(
                isSignInSuccessful = signInResult.data != null,
                errorMessage = signInResult.errorMessage
            )
        }
    }

    suspend fun getSignInIntentSender(): IntentSender? {
        val intentSender = googleAuthUiClient.beginSignIn()
        if(intentSender == null){
            uiState = uiState.copy(errorMessage = "Error while starting sign in")
        }
        return intentSender
    }

    fun updateIsLoading(isLoading: Boolean) {
        uiState = uiState.copy(
            isLoading = isLoading
        )
    }
}