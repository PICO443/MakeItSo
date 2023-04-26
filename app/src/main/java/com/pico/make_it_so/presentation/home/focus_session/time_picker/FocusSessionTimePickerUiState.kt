package com.pico.make_it_so.presentation.home.focus_session.time_picker

import com.pico.make_it_so.domain.model.Task
import kotlinx.coroutines.flow.Flow

data class FocusSessionTimePickerUiState(
    val sessionTimeMinutes: Int = 30,
    val tasks: Flow<List<Task>>,
    val selectedTask: Task? = null
)
