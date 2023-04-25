package com.pico.make_it_so.presentation.home.settings

import com.pico.make_it_so.domain.model.User

data class SettingsUiState(
    val user: User? = null,
    val isLoggedIn: Boolean = false
)