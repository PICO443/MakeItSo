package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import com.pico.make_it_so.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(AddEditTaskUiState())
        private set

    init {
        val addEditTaskNavArgs: AddEditTaskNavArgs = savedStateHandle.navArgs()
        addEditTaskNavArgs.task?.apply {
            uiState = uiState.copy(
                editing = true,
                taskId = id,
                taskTimestamp = dueDate,
                taskTitle = title,
                taskDescription = description
            )
        }
    }

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
                    val task = Task(
                        title = uiState.taskTitle,
                        description = uiState.taskDescription,
                        dueDate = uiState.taskTimestamp
                    )
                    if (uiState.editing) {
                        taskRepository.updateTask(task.copy(id = uiState.taskId!!))
                    } else {
                        taskRepository.addTask(task)
                    }
                    event.onSuccess()
                }
            }
            is AddEditTaskEvent.OnTaskDateChange -> {
                uiState = uiState.copy(
                    taskTimestamp = Timestamp(Date(event.taskTimestampMillis))
                )
            }
            is AddEditTaskEvent.InitializeWith -> {
                uiState = uiState.copy(
                    editing = true,
                    taskId = event.task.id,
                    taskTimestamp = event.task.dueDate,
                    taskTitle = event.task.title,
                    taskDescription = event.task.description
                )
            }
        }
    }
}

sealed class AddEditTaskEvent() {
    class OnTitleChange(val title: String) : AddEditTaskEvent()
    class OnDescriptionChange(val description: String) : AddEditTaskEvent()
    class OnTaskDateChange(val taskTimestampMillis: Long) : AddEditTaskEvent()

    class InitializeWith(val task: Task) : AddEditTaskEvent()
    class SaveTask(val onSuccess: () -> Unit) : AddEditTaskEvent()
}