package com.pico.make_it_so.presentation.home.focus_session.time_picker.session

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation._nav_graphs.FocusSessionNavGraph
import com.pico.make_it_so.presentation.home.focus_session.time_picker.session.components.CustomCircularProgressIndicator
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@FocusSessionNavGraph(start = true)
@Destination(
    navArgsDelegate = FocusSessionNavArgs::class
)
@Composable
fun FocusSessionScreen(
    viewModel: FocusSessionViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FocusSessionEvent.StartSession(onSessionEnd = { navigator.navigateUp() }))
    }

    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            uiState.task?.apply {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "${uiState.sessionState.label} ${uiState.sessionState.num} of ${uiState.sessionState.totalNum}",
            )
            CustomCircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                label = "${uiState.remainingTime.millisToMinutes()}m  ",
                percentage = uiState.remainingTimePercentage,
                primaryColor = MaterialTheme.colorScheme.primary,
                secondaryColor = MaterialTheme.colorScheme.surface,
                circleRadius = 150f,
                onPositionChange = { position ->
                    // Do something with the new position
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                FilledIconButton(onClick = { viewModel.onEvent(FocusSessionEvent.TogglePause) }) {
                    Icon(
                        painter = painterResource(if (uiState.isPaused) R.drawable.play_arrow_fill1_wght400_grad0_opsz24 else R.drawable.pause_fill1_wght400_grad0_opsz24),
                        contentDescription = null
                    )
                }
                OutlinedIconButton(onClick = {
                    viewModel.onEvent(
                        FocusSessionEvent.EndSession(
                            onSuccess = { navigator.navigateUp() })
                    )
                }) {
                    Icon(
                        painter = painterResource(R.drawable.stop_fill0_wght400_grad0_opsz24),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FocusSessionPrev() {
    MakeItSoTheme {
//        FocusSessionScreen()
    }
}