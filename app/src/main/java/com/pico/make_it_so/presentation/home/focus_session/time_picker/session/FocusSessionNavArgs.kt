package com.pico.make_it_so.presentation.home.focus_session.time_picker.session

import com.pico.make_it_so.domain.model.Task

data class FocusSessionNavArgs(
    val task: Task?,
    val sessionTimeMinutes: Int = 0
)
