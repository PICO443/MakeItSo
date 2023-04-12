package com.pico.make_it_so.presentation.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation._nav_graphs.HomeNavGraph
import com.pico.make_it_so.presentation.settings.components.SettingOption
import com.pico.make_it_so.presentation.settings.components.SettingsTopAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@HomeNavGraph
@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            SettingsTopAppBar { navigator.navigateUp() }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.song_cover),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentDescription = null
                    )
                    Column() {
                        Text(text = "Abubakr Elsadig", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "abubakrko32@gmail.com",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            SettingOption(
                settingOption = SettingOption(
                    label = "Login",
                    icon = R.drawable.login_black_24dp
                )
            )
            Divider()
            SettingOption(
                settingOption = SettingOption(
                    label = "Sign Up",
                    icon = R.drawable.person_add_black_24dp
                )
            )
        }
    }
}