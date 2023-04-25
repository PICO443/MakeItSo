package com.pico.make_it_so.presentation.home.home.task.task_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var uiState by mutableStateOf(
        TaskDetailsUiState(
            task = savedStateHandle.navArgs<TaskDetailsNavArgs>().task ?: Task()
        )
    )

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