package com.pico.make_it_so.presentation.sign_up.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopAppBar(navigateBack: () -> Unit) {
    TopAppBar(title = { Text(text = "Sign Up") }, navigationIcon = {
        IconButton(onClick = navigateBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
}