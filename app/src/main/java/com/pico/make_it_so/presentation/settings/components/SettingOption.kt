package com.pico.make_it_so.presentation.settings.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SettingOption(settingOption: SettingOption,modifier: Modifier = Modifier){
    Row(modifier = modifier.padding(16.dp)) {
        Text(text = settingOption.label, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(4.dp))
        Icon(painter = painterResource(id = settingOption.icon), contentDescription = null)
    }
}

data class SettingOption(val label: String, @DrawableRes val icon: Int)