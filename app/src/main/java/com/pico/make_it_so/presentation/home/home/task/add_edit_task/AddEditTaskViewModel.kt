package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    var uiState by mutableStateOf(AddEditTaskUiState())
        private set

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.OnTitleChange -> {
                uiState = uiState.copy(taskTitle = event.title, hasTaskTitleError = false)
            }
            is AddEditTaskEvent.OnDescriptionChange -> {
                uiState = uiState.copy(taskDescription = event.description)
            }
            is AddEditTaskEvent.SaveTask -> {
                if (uiState.taskTitle.isEmpty()) {
                    uiState = uiState.copy(hasTaskTitleError = true)
                    return
                }
                viewModelScope.launch {
                    taskRepository.addTask(
                        task = Task(
                            title = uiState.taskTitle,
                            description = uiState.taskDescription,
                            dueDate = uiState.taskTimestamp
                        )
                    )
                    event.onSuccess()
                }
            }
            is AddEditTaskEvent.OnTaskDateChange -> {
                uiState = uiState.copy(
                    taskTimestamp = Timestamp(Date(event.taskTimestampMillis))
                )
            }
        }
    }
}

sealed class AddEditTaskEvent() {
    class OnTitleChange(val title: String) : AddEditTaskEvent()
    class OnDescriptionChange(val description: String) : AddEditTaskEvent()
    class OnTaskDateChange(val taskTimestampMillis: Long) : AddEditTaskEvent()
    class SaveTask(val onSuccess: () -> Unit) : AddEditTaskEvent()
}