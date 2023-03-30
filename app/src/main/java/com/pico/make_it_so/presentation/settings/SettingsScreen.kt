package com.pico.make_it_so.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation.settings.components.SettingOption
import com.pico.make_it_so.presentation.settings.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(), navigateBack: () -> Unit){
    val uiState = viewModel.uiState
    var settingsOptions by remember {
        mutableStateOf(listOf<SettingOption>())
    }
    LaunchedEffect(key1 = uiState.isLoggedIn){
        if(uiState.isLoggedIn.not()){
            val settings = mutableListOf<SettingOption>()
            settings.add(SettingOption("Login", R.drawable.login_black_24dp))
            settings.add(SettingOption("Sign up", R.drawable.person_add_black_24dp))
            settingsOptions = settings
        }
    }
    Scaffold(
        topBar = {
            SettingsTopAppBar(navigateBack)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            settingsOptions.forEach {
                SettingOption(settingOption = it)
                Divider(Modifier.padding(vertical = 16.dp))
            }
        }
    }
}