package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val accountService: AccountService
) :
    ViewModel() {
    var uiState by mutableStateOf(AddEditTaskUiState())
        private set

    suspend fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.OnTitleChange -> {
                uiState = uiState.copy(taskTitle = event.title)
            }
            is AddEditTaskEvent.OnDescriptionChange -> {
                uiState = uiState.copy(taskDescription = event.description)
            }
            is AddEditTaskEvent.SaveTask -> {
                taskRepository.addTask(
                    task = Task(
                        title = uiState.taskTitle,
                        description = uiState.taskDescription
                    )
                )
            }
            is AddEditTaskEvent.OnTaskDateChange -> {
                uiState = uiState.copy(
                    taskDateMillis = event.taskDateMillis
                )
            }
            is AddEditTaskEvent.OnTaskDurationChange -> {

            }
        }
    }
}

sealed class AddEditTaskEvent() {
    class OnTitleChange(val title: String) : AddEditTaskEvent()
    class OnDescriptionChange(val description: String) : AddEditTaskEvent()
    class OnTaskDateChange(val taskDateMillis: Long) : AddEditTaskEvent()
    class OnTaskDurationChange(val taskDurationMinutes: Int) : AddEditTaskEvent()
    object SaveTask : AddEditTaskEvent()
}