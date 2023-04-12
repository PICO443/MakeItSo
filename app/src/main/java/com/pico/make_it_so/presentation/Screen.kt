package com.pico.make_it_so.presentation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.pico.make_it_so.R

sealed class Screen(val route: String, val name: String, val icon: ImageVector? = null) {
    object SignUpScreen : Screen("sign_up", "Sign Up")
    object LoginScreen : Screen("login", "Login")
    object SettingsScreen : Screen("settings", "Settings", Icons.Default.Settings)
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Splash : Screen("splash", "Splash")
    object AddEditScreen : Screen("add_edit", "Add/Edit")
}
