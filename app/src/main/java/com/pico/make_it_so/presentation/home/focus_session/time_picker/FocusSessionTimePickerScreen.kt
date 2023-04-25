package com.pico.make_it_so.presentation.home.focus_session.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.core.FOCUS_SESSION_ROUND_TIME_MINUTES
import com.pico.make_it_so.presentation._nav_graphs.HomeNavGraph
import com.pico.make_it_so.presentation.destinations.FocusSessionScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@HomeNavGraph
@Destination(style = FocusSessionTransition::class)
@Composable
fun FocusSessionTimePicker(
    viewModel: FocusSessionTimePickerViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
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
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            }
            Button(onClick = {
                navigator.navigate(FocusSessionScreenDestination(sessionTimeMinutes = uiState.sessionTimeMinutes))
            }) {
                Text(text = "Start")
            }
        }
    }
}