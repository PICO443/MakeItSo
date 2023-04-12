package com.pico.make_it_so.presentation.log_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.presentation._common.EmailTextField
import com.pico.make_it_so.presentation._common.PasswordTextField
import com.pico.make_it_so.presentation.log_in.components.LoginTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState = viewModel.uiState
    Scaffold(
        topBar = {
            LoginTopAppBar(navigateBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            EmailTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEvent(LoginEvent.OnEmailChange(it)) })
            PasswordTextField(
                value = uiState.password,
                onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) })
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.onEvent(LoginEvent.Login(onSuccessLogin = { navigateToHome() })) }) {
                Text(text = "LogIn")
            }
        }
    }
}