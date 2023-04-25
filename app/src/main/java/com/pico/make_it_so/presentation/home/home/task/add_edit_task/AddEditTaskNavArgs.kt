package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import android.os.Parcelable
import com.pico.make_it_so.domain.model.Task
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddEditTaskNavArgs(
    val task: Task? = null
) : Parcelable
