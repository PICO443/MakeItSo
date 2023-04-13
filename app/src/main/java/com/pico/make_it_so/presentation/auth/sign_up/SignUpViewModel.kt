package com.pico.make_it_so.presentation.auth.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.make_it_so.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUp -> {
                viewModelScope.launch {
                    signUpUseCase(uiState.email, uiState.password)
                }
            }
            is SignUpEvent.OnPasswordChange -> {
                uiState = uiState.copy(password = event.password)
            }
            is SignUpEvent.OnEmailChange -> {
                uiState = uiState.copy(email = event.email)
            }
            is SignUpEvent.OnRepeatPasswordChange -> {
                uiState = uiState.copy(repeatedPassword = event.repeatedPassword)
            }
        }
    }
}

sealed class SignUpEvent {
    class OnEmailChange(val email: String) : SignUpEvent()
    class OnPasswordChange(val password: String) : SignUpEvent()
    class OnRepeatPasswordChange(val repeatedPassword: String) : SignUpEvent()
    object SignUp : SignUpEvent()
}