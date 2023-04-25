package com.pico.make_it_so.presentation.on_boarding.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.presentation.NavGraphs
import com.pico.make_it_so.presentation._nav_graphs.OnBoardingNavGraph
import com.pico.make_it_so.presentation.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@OnBoardingNavGraph(start = true)
@Destination(style = SplashScreenTransition::class)
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState
    LaunchedEffect(key1 = Unit) {
        if (uiState.isLoggedIn) {
            navigator.navigate(NavGraphs.home) {
                popUpTo(SplashScreenDestination.route) { inclusive = true }
            }
        }
    }
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(text = "state: ${uiState.isLoggedIn}")
            Button(onClick = {
                navigator.navigate(NavGraphs.auth)
            }) {
                Text(text = "Sign Up")
            }
            Button(onClick = {
                viewModel.onEvent(SplashEvent.LoginAnonymously(onLoginSuccess = {
                    navigator.navigate(NavGraphs.home) {
                        popUpTo(SplashScreenDestination.route) { inclusive = true }
                    }
                }))
            }) {
                Text(text = "Continue Offline")
            }
        }
    }
}