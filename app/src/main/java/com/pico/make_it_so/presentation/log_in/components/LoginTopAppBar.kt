package com.pico.make_it_so.presentation.log_in.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar(onNavigateBack: () -> Unit) {
    TopAppBar(title = { Text(text = "LogIn") }, navigationIcon = {
        IconButton(onClick = onNavigateBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
}