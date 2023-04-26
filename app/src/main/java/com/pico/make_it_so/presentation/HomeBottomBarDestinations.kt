package com.pico.make_it_so.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation.destinations.FocusSessionTimePickerDestination
import com.pico.make_it_so.presentation.destinations.HomeScreenDestination
import com.pico.make_it_so.presentation.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class HomeBottomBarDestinations(
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @DrawableRes val filledIcon: Int,
    @StringRes val label: Int
) {
    Home(
        direction = HomeScreenDestination,
        icon = R.drawable.home_fill0_wght400_grad0_opsz24,
        filledIcon = R.drawable.home_fill1_wght400_grad0_opsz24,
        label = R.string.home_screen_label
    ),
    FocusSession(
        direction = FocusSessionTimePickerDestination,
        icon = R.drawable.mindfulness_fill0_wght400_grad0_opsz24,
        filledIcon = R.drawable.mindfulness_fill1_wght400_grad0_opsz24,
        label = R.string.focus_session_screen_label
    ),
    Settings(
        direction = SettingsScreenDestination,
        icon = R.drawable.settings_fill0_wght400_grad0_opsz24,
        filledIcon = R.drawable.settings_fill1_wght400_grad0_opsz24,
        label = R.string.settings_screen_label
    )
}