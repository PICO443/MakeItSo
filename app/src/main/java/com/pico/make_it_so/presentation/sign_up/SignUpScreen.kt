package com.pico.make_it_so.presentation.sign_up

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
import com.pico.make_it_so.presentation.sign_up.components.EmailTextField
import com.pico.make_it_so.presentation.sign_up.components.PasswordTextField
import com.pico.make_it_so.presentation.sign_up.components.SignUpTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), navigateBack: () -> Unit) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            SignUpTopAppBar(navigateBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEvent(SignUpEvent.OnEmailChange(it)) },
                placeholder = "Email"
            )
            PasswordTextField(
                value = uiState.password,
                onValueChange = { viewModel.onEvent(SignUpEvent.OnPasswordChange(it)) },
                placeholder = "Password"
            )
            PasswordTextField(
                value = uiState.repeatedPassword,
                onValueChange = { viewModel.onEvent(SignUpEvent.OnRepeatPasswordChange(it)) },
                placeholder = "Repeat Password"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.onEvent(SignUpEvent.SignUp) }) {
                Text(text = "Sign Up")
            }
        }
    }
}
