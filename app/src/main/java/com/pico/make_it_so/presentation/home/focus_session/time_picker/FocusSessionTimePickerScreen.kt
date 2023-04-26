package com.pico.make_it_so.presentation.home.focus_session.time_picker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.core.FOCUS_SESSION_ROUND_TIME_MINUTES
import com.pico.make_it_so.presentation._nav_graphs.HomeNavGraph
import com.pico.make_it_so.presentation.destinations.FocusSessionScreenDestination
import com.pico.make_it_so.presentation.home.focus_session.time_picker.components.FocusSessionTaskListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@HomeNavGraph
@Destination(style = FocusSessionTransition::class)
@Composable
fun FocusSessionTimePicker(
    viewModel: FocusSessionTimePickerViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState
    val tasks by uiState.tasks.collectAsState(initial = emptyList())
    Scaffold() { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                ) {
                    Text(text = "Get ready to focus", style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "We'll turn off notifications and alerts during longer sessions to get an effective focus session",
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FilledIconButton(onClick = {
                        viewModel.onEvent(
                            FocusSessionTimePickerEvent.OnSessionTimeChange(
                                uiState.sessionTimeMinutes.plus(
                                    FOCUS_SESSION_ROUND_TIME_MINUTES
                                )
                            )
                        )
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    OutlinedTextField(
                        value = uiState.sessionTimeMinutes.toString(),
                        readOnly = true,
                        onValueChange = {
                            viewModel.onEvent(
                                FocusSessionTimePickerEvent.OnSessionTimeChange(
                                    it.toInt()
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    FilledIconButton(
                        enabled = uiState.sessionTimeMinutes > 0,
                        onClick = {
                            viewModel.onEvent(
                                FocusSessionTimePickerEvent.OnSessionTimeChange(
                                    uiState.sessionTimeMinutes.minus(
                                        FOCUS_SESSION_ROUND_TIME_MINUTES
                                    )
                                )
                            )
                        }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
                Button(onClick = {
                    navigator.navigate(
                        FocusSessionScreenDestination(
                            task = uiState.selectedTask,
                            sessionTimeMinutes = uiState.sessionTimeMinutes
                        )
                    )
                }) {
                    Text(text = "Start")
                }
            }
            item {
                Text(text = "Select a task for the focus session", textAlign = TextAlign.Start)
                Surface(
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = 3.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            space = 8.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        tasks.forEach { task ->
                            FocusSessionTaskListItem(
                                task = task,
                                isSelected = task == uiState.selectedTask,
                                onClick = {
                                    viewModel.onEvent(
                                        FocusSessionTimePickerEvent.ToggleSelectTask(
                                            it
                                        )
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}