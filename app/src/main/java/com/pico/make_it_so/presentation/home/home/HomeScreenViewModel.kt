package com.pico.make_it_so.presentation.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.pico.make_it_so.core.ALL_TASKS_KEY
import com.pico.make_it_so.core.TODAY_KEY
import com.pico.make_it_so.core.TOMORROW_KEY
import com.pico.make_it_so.core.getDate
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) :
    ViewModel() {

    var uiState by mutableStateOf(HomeUiState(tasks = taskRepository.tasks.map { taskList ->
        taskList.groupBy {
            when (getDate(it.dueDate.toDate().time)) {
                TODAY_KEY -> {
                    TaskGroup.TODAY
                }
                TOMORROW_KEY -> {
                    TaskGroup.TOMORROW
                }
                else -> {
                    TaskGroup.ALL
                }
            }
        }
    }))
        private set

}