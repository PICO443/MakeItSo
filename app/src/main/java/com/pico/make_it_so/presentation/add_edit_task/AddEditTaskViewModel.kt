package com.pico.make_it_so.presentation.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val accountService: AccountService
) :
    ViewModel() {
    var uiState by mutableStateOf(AddEditTaskUiState())
        private set

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.TitleChanged -> {
                uiState = uiState.copy(taskTitle = event.title)
            }
            is AddEditTaskEvent.DescriptionChanged -> {
                uiState = uiState.copy(taskDescription = event.description)
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    taskRepository.addTask(
                        task = Task(
                            userId = accountService.currentUser?.uid.orEmpty(),
                            title = uiState.taskTitle,
                            description = uiState.taskDescription
                        )
                    )
                }
            }
        }
    }
}

sealed class AddEditTaskEvent() {
    class TitleChanged(val title: String) : AddEditTaskEvent()
    class DescriptionChanged(val description: String) : AddEditTaskEvent()
    object SaveTask : AddEditTaskEvent()
}