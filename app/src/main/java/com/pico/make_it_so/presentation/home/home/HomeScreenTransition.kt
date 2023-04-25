package com.pico.make_it_so.presentation.home.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.pico.make_it_so.presentation.NavGraphs
import com.pico.make_it_so.presentation.appDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object HomeScreenTransition : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return if (NavGraphs.home.destinations.contains(initialState.appDestination())) {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(700)
            )
        } else {
            fadeIn(animationSpec = tween(400))
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(700)
        )
    }
}