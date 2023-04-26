package com.pico.make_it_so.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.pico.make_it_so.presentation.on_boarding.splash.SplashViewModel
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeItSoTheme {
                val animationEngine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
                )
                val navController = rememberAnimatedNavController()
                val currentDestination by navController.appCurrentDestinationAsState()
                val splashScreenViewModel: SplashViewModel = hiltViewModel()
                val uiState = splashScreenViewModel.uiState
                Scaffold(
                    bottomBar = {
                        currentDestination?.let {
                            if (NavGraphs.home.destinations.contains(it))
                                BottomNavBar(navController = navController)
                        }
                    }
                ) {
                    DestinationsNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startRoute = if(uiState.isLoggedIn) NavGraphs.home else NavGraphs.onBoarding,
                        navGraph = NavGraphs.root,
                        engine = animationEngine
                    )
                }
            }
        }
    }
}