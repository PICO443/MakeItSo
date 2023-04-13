package com.pico.make_it_so.presentation.on_boarding.splash

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object SplashScreenTransition: DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return slideOutHorizontally(
            targetOffsetX = { -1000 },
            animationSpec = tween(2000)
        )
    }
}