package com.pico.make_it_so.presentation.home.focus_session.time_picker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FocusSessionTimePickerViewModel @Inject constructor(
    taskRepository: TaskRepository
) : ViewModel() {
    var uiState by mutableStateOf(FocusSessionTimePickerUiState(tasks = taskRepository.tasks))
        private set

    fun onEvent(event: FocusSessionTimePickerEvent) {
        when (event) {
            is FocusSessionTimePickerEvent.OnSessionTimeChange -> {
                uiState = uiState.copy(sessionTimeMinutes = event.sessionTimeMinutes)
            }
            is FocusSessionTimePickerEvent.ToggleSelectTask -> {
                uiState = if (uiState.selectedTask == event.task)
                    uiState.copy(selectedTask = null)
                else
                    uiState.copy(selectedTask = event.task)
            }
        }
    }
}

sealed class FocusSessionTimePickerEvent {
    class OnSessionTimeChange(val sessionTimeMinutes: Int) : FocusSessionTimePickerEvent()
    class ToggleSelectTask(val task: Task) : FocusSessionTimePickerEvent()
}