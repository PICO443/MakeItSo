package com.pico.make_it_so.presentation.home.home.task.task_details

import android.os.Parcelable
import com.pico.make_it_so.domain.model.Task
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskDetailsNavArgs(
    val task: Task? = null
) : Parcelable
