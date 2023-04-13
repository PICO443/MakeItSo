package com.pico.make_it_so.presentation.auth.google_sign_in

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.presentation._nav_graphs.AuthNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@AuthNavGraph(start = true)
@Destination
@Composable
fun GoogleSignInScreen(
    viewModel: GoogleSignInViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    viewModel.onSignInResult(result.data)
                }
            }
        }
    )

    LaunchedEffect(key1 = uiState) {
        uiState.errorMessage?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            coroutineScope.launch {
                val signInIntentSender = viewModel.getSignInIntentSender()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }) {
            Text(text = "Sign in")
        }
    }
}