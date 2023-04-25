package com.pico.make_it_so.presentation.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.make_it_so.core.FINISHED_TASK_GROUP_KEY
import com.pico.make_it_so.core.TODAY_KEY
import com.pico.make_it_so.core.TOMORROW_KEY
import com.pico.make_it_so.core.getDate
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) :
    ViewModel() {

    var uiState by mutableStateOf(HomeUiState(tasks = taskRepository.tasks.map { taskList ->
        val groups = taskList.groupBy {
            if (it.completed) FINISHED_TASK_GROUP_KEY
            else
                getDate(it.dueDate.toDate().time)
        }.toMutableMap()
        val sortedMap = emptyMap<String, List<Task>>().toMutableMap()
        groups[TODAY_KEY]?.let {
            sortedMap[TODAY_KEY] = it
            groups.remove(TODAY_KEY)
        }
        groups[TOMORROW_KEY]?.let {
            sortedMap[TOMORROW_KEY] = it
            groups.remove(TOMORROW_KEY)
        }
        sortedMap.putAll(groups)
        sortedMap
    }))
        private set

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnTaskCompleteChange -> {
                viewModelScope.launch {
                    taskRepository.setTaskAsCompleted(event.task)
                }
            }
        }
    }

}

sealed class HomeEvent {
    class OnTaskCompleteChange(val task: Task) : HomeEvent()
}