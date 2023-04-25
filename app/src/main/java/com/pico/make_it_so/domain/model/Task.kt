package com.pico.make_it_so.domain.model

import androidx.compose.runtime.Stable
import com.google.firebase.Timestamp

@Stable
data class Task(
    val id: String = "",
    val title: String = "",
    val description: String? = null,
    val dueDate: Timestamp = Timestamp.now(),
    val timeTookInSeconds: Int = 0,
    val completed: Boolean = false,
)
