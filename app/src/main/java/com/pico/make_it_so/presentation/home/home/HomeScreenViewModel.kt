package com.pico.make_it_so.presentation.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) :
    ViewModel() {

    var uiState by mutableStateOf(HomeUiState(tasks = taskRepository.tasks))
        private set

}