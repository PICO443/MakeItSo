package com.pico.make_it_so.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Task(
    val id: String = "",
    val title: String = "",
    val priority: String = "",
    val dueDate: String = "",
    val dueTime: String = "",
    val description: String = "",
    val url: String = "",
    val flag: Boolean = false,
    val completed: Boolean = false,
)
