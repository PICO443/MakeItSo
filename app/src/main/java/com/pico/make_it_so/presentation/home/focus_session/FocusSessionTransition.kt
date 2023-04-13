package com.pico.make_it_so.presentation.home.focus_session

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.pico.make_it_so.presentation.appDestination
import com.pico.make_it_so.presentation.destinations.HomeScreenDestination
import com.pico.make_it_so.presentation.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object FocusSessionTransition: DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return when(initialState.appDestination()){
            is HomeScreenDestination -> {
                slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(700))
            }
            is SettingsScreenDestination -> {
                slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(700))
            }
            else -> {
                fadeIn(tween(700))
            }
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when(initialState.appDestination()){
            is HomeScreenDestination -> {
                slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(700))
            }
            is SettingsScreenDestination -> {
                slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(700))
            }
            else -> {
                fadeOut()
            }
        }
    }
}