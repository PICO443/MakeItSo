package com.pico.make_it_so.presentation.home.focus_session.time_picker.session

import com.pico.make_it_so.domain.model.Task

data class FocusSessionUiState(
    val totalSessionTimeMinutes: Int = 0,
    val timeSpentSeconds: Int = 0,
    val task: Task? = null,
    val sessionState: SessionState = SessionState.Idle,
    val remainingTime: Long = 0,
    val remainingTimePercentage: Int = 100,
    val isPaused: Boolean = true
)

sealed class SessionState(val label: String, val num: Int, val totalNum: Int) {
    class Round(num: Int, totalNum: Int) : SessionState("Round", num, totalNum)
    class Break(num: Int, totalNum: Int) : SessionState("Break", num, totalNum)
    object Idle : SessionState("Idle", -1, -1)
}

fun Long.millisToMinutes(): Int {
    return (this / 1000f / 60f).toInt()
}
