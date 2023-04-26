package com.pico.make_it_so.presentation.on_boarding.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation.NavGraphs
import com.pico.make_it_so.presentation._nav_graphs.OnBoardingNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OnBoardingNavGraph(start = true)
@Destination(style = SplashScreenTransition::class)
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.complete),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f)
                )
                Text(
                    text = "We’ll help you to track and accomplish your tasks",
                    softWrap = true,
                    textAlign = TextAlign.Center
                )
            }
            Button(onClick = {
                navigator.navigate(NavGraphs.auth)
            }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.google__g__logo),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Continue with google")
            }
        }
    }
}