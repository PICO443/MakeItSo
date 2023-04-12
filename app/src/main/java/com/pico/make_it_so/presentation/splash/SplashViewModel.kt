package com.pico.make_it_so.presentation.splash

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
class SplashViewModel @Inject constructor(private val accountUseCases: AccountUseCases) :
    ViewModel() {
    var uiState by mutableStateOf(SplashUiState(accountUseCases.getCurrentUserUseCase() != null))

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.LoginAnonymously -> {
                viewModelScope.launch {
                    accountUseCases.loginAnonymouslyUseCase()
                }
            }
        }
    }
}

sealed class SplashEvent {
    class LoginAnonymously(onLoginSuccess: () -> Unit) : SplashEvent()
}