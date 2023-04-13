package com.pico.make_it_so.presentation.home.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar() {
    TopAppBar(title = { Text(text = "Tasks") })
}