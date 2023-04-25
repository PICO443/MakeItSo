package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import com.google.firebase.Timestamp

data class AddEditTaskUiState(
    val taskTitle: String = "",
    val hasTaskTitleError: Boolean = false,
    val taskDescription: String? = null,
    val taskTimestamp: Timestamp = Timestamp.now(),
)
