package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import java.util.*

data class AddEditTaskUiState(
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskDateMillis: Long = Calendar.getInstance().timeInMillis,
    val taskDurationMinutes: Int = 0
)
