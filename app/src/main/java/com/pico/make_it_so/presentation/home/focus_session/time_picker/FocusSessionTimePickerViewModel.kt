package com.pico.make_it_so.presentation.home.focus_session.time_picker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FocusSessionTimePickerViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(FocusSessionTimePickerUiState())
        private set

    fun onEvent(event: FocusSessionTimePickerEvent) {
        when (event) {
            is FocusSessionTimePickerEvent.OnSessionTimeChange -> {
                uiState = uiState.copy(sessionTimeMinutes = event.sessionTimeMinutes)
            }
        }
    }
}

sealed class FocusSessionTimePickerEvent {
    class OnSessionTimeChange(val sessionTimeMinutes: Int) : FocusSessionTimePickerEvent()
}