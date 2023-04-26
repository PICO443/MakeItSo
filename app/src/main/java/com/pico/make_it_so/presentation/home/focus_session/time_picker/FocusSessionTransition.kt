package com.pico.make_it_so.presentation.home.focus_session.time_picker

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.pico.make_it_so.core.TRANSITION_TIME_MILLIS
import com.pico.make_it_so.presentation.appDestination
import com.pico.make_it_so.presentation.destinations.HomeScreenDestination
import com.pico.make_it_so.presentation.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object FocusSessionTransition: DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        println(initialState.appDestination().route)
        return when(initialState.appDestination()){
            is HomeScreenDestination -> {
                slideInHorizontally(initialOffsetX = {it}, animationSpec = tween(
                    TRANSITION_TIME_MILLIS
                ))
            }
            is SettingsScreenDestination -> {
                slideInHorizontally(initialOffsetX = {-it}, animationSpec = tween(TRANSITION_TIME_MILLIS))
            }
            else -> {
                fadeIn(tween(TRANSITION_TIME_MILLIS))
            }
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when(targetState.appDestination()){
            is HomeScreenDestination -> {
                slideOutHorizontally(targetOffsetX = {it}, animationSpec = tween(700))
            }
            is SettingsScreenDestination -> {
                slideOutHorizontally(targetOffsetX = {-it}, animationSpec = tween(700))
            }
            else -> {
                fadeOut()
            }
        }
    }
}