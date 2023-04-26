package com.pico.make_it_so.presentation.home.focus_session.time_picker.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pico.make_it_so.core.FOCUS_SESSION_BREAK_TIME_MINUTES
import com.pico.make_it_so.core.FOCUS_SESSION_ROUND_TIME_MINUTES
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import com.pico.make_it_so.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class FocusSessionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val taskRepository: TaskRepository
) : ViewModel() {

    var uiState by mutableStateOf(FocusSessionUiState())
        private set

    init {
        val focusSessionNavArgs: FocusSessionNavArgs = savedStateHandle.navArgs()
        uiState = uiState.copy(
            task = focusSessionNavArgs.task,
            totalSessionTimeMinutes = focusSessionNavArgs.sessionTimeMinutes
        )
    }

    fun onEvent(event: FocusSessionEvent) {
        when (event) {
            is FocusSessionEvent.StartSession -> {
                if (uiState.totalSessionTimeMinutes > 0)
                    startSession(uiState.totalSessionTimeMinutes, onSessionEnd = event.onSessionEnd)
            }
            is FocusSessionEvent.TogglePause -> {
                uiState = uiState.copy(
                    isPaused = uiState.isPaused.not()
                )
            }
            is FocusSessionEvent.EndSession -> {
                uiState.task?.let {
                    viewModelScope.launch {
                        updateTaskSpentTime(it)
                    }
                }
                event.onSuccess()
            }
        }
    }

    private fun startSession(sessionTime: Int, onSessionEnd: () -> Unit) {
        val numOfRounds = (sessionTime / FOCUS_SESSION_ROUND_TIME_MINUTES)
        viewModelScope.launch {
            uiState = uiState.copy(isPaused = false)
            for (roundNum in 1..numOfRounds) {
                // Start a round
                uiState = uiState.copy(sessionState = SessionState.Round(roundNum, numOfRounds))
                timerFor(FOCUS_SESSION_ROUND_TIME_MINUTES - FOCUS_SESSION_BREAK_TIME_MINUTES)
                // Start a break
                if (roundNum != numOfRounds) {
                    uiState =
                        uiState.copy(sessionState = SessionState.Break(roundNum, numOfRounds - 1))
                    timerFor(FOCUS_SESSION_BREAK_TIME_MINUTES)
                }
            }
            onSessionEnd()
            uiState.task?.let {
                updateTaskSpentTime(it)
            }
            uiState = uiState.copy(sessionState = SessionState.Idle)
        }
    }

    private suspend fun timerFor(timeMinutes: Int) {
        val timeMillis = timeMinutes.toLong() * 60 * 1000
        val second = 1000
        uiState = uiState.copy(remainingTime = timeMillis)
        while (uiState.remainingTime > 0) {
            delay(1000)
            if (uiState.isPaused.not()) {
                val remainingTime = uiState.remainingTime.minus(second)
                uiState = uiState.copy(
                    remainingTime = remainingTime,
                    remainingTimePercentage = (remainingTime.toFloat() / timeMillis * 100).toInt(),
                    timeSpentSeconds = (uiState.timeSpentSeconds + second) / 1000
                )
            }
        }
    }

    private suspend fun updateTaskSpentTime(task: Task) {
        val minutes = ceil(uiState.timeSpentSeconds / 60f)
        taskRepository.updateTask(task.copy(timeSpentInMinutes = task.timeSpentInMinutes + minutes.toInt()))
    }

}

sealed class FocusSessionEvent {
    class StartSession(val onSessionEnd: () -> Unit) : FocusSessionEvent()
    object TogglePause : FocusSessionEvent()
    class EndSession(val onSuccess: () -> Unit) : FocusSessionEvent()
}