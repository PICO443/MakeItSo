package com.pico.make_it_so.domain.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String = "",
    val title: String = "",
    val description: String? = null,
    val dueDate: Timestamp = Timestamp.now(),
    val timeSpentInMinutes: Int = 20,
    val completed: Boolean = false,
) : Parcelable
