package com.pico.make_it_so.presentation.log_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.make_it_so.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountUseCases: AccountUseCases) :
    ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                uiState = uiState.copy(email = event.email)
            }
            is LoginEvent.OnPasswordChange -> {
                uiState = uiState.copy(password = event.password)
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    accountUseCases.loginUseCase(uiState.email, uiState.password)
                    event.onSuccessLogin()
                }
            }
        }
    }

}

sealed class LoginEvent {
    class OnEmailChange(val email: String) : LoginEvent()
    class OnPasswordChange(val password: String) : LoginEvent()
    class Login(val onSuccessLogin: () -> Unit) : LoginEvent()
}