package com.pico.make_it_so.presentation.add_edit_task.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskTopAppBar(navigateBack: () -> Unit) {
    TopAppBar(title = { Text(text = "Add New Task") }, navigationIcon = {
        IconButton(onClick = navigateBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
}