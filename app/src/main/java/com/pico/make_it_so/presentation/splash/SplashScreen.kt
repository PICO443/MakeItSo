package com.pico.make_it_so.presentation.splash

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    val uiState = viewModel.uiState
    LaunchedEffect(key1 = Unit) {
        if (uiState.isLoggedIn) {
            navigateToHome()
        }
    }
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(text = "state: ${uiState.isLoggedIn}")
            Button(onClick = navigateToSignUp) {
                Text(text = "Sign Up")
            }
            Button(onClick = {
                viewModel.onEvent(SplashEvent.LoginAnonymously(onLoginSuccess = {
                    navigateToHome()
                }))
            }) {
                Text(text = "Continue Offline")
            }
        }
    }
}