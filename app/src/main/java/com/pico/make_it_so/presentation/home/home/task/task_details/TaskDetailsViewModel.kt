package com.pico.make_it_so.presentation.home.home.task.task_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(TaskDetailsUiState())

    fun onEvent(event: TaskDetailsEvent) {
        when (event) {
            is TaskDetailsEvent.OnTaskChange -> {
                uiState = uiState.copy(task = event.task)
            }
        }
    }
}

sealed class TaskDetailsEvent {
    class OnTaskChange(val task: Task) : TaskDetailsEvent()
}