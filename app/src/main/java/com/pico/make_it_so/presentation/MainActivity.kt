package com.pico.make_it_so.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class,
        ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeItSoTheme {
                val animationEngine = rememberAnimatedNavHostEngine()
                val navController = rememberAnimatedNavController()
                val currentDestination by navController.appCurrentDestinationAsState()
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
                        navGraph = NavGraphs.root,
                        engine = animationEngine
                    )
                }
            }
        }
    }
}