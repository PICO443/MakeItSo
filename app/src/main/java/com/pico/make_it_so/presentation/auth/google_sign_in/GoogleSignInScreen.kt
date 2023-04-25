package com.pico.make_it_so.presentation.auth.google_sign_in

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.presentation.NavGraphs
import com.pico.make_it_so.presentation._nav_graphs.AuthNavGraph
import com.pico.make_it_so.presentation.destinations.GoogleSignInScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@AuthNavGraph(start = true)
@Destination
@Composable
fun GoogleSignInScreen(
    viewModel: GoogleSignInViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
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
                    viewModel.updateIsLoading(false)
                }
            }
            if(result.resultCode == RESULT_CANCELED){
                navigator.navigateUp()
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.updateIsLoading(true)
        val signInIntentSender = viewModel.getSignInIntentSender()
        signInIntentSender?.let {
            launcher.launch(
                IntentSenderRequest.Builder(it).build()
            )
        }
    }

    LaunchedEffect(key1 = uiState.errorMessage, key2 = uiState.isSignInSuccessful) {
        uiState.errorMessage?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
        if (uiState.isSignInSuccessful) {
            navigator.navigate(NavGraphs.home) {
                popUpTo(GoogleSignInScreenDestination.route) { inclusive = true }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        if(uiState.isLoading){
            Text(text = "Signing in with google ...", style = MaterialTheme.typography.titleLarge)
            CircularProgressIndicator()
        }
        uiState.errorMessage?.run { 
            Text(text = this)
        }
    }
}