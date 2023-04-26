package com.pico.make_it_so.presentation.home.settings.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar(navigateBack: () -> Unit) {
    TopAppBar(title = { Text(text = "Settings") })
}