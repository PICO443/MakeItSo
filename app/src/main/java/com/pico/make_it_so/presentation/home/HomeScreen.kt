package com.pico.make_it_so.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.presentation.home.components.HomeScreenTopAppBar
import com.pico.make_it_so.presentation.home.components.TaskListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToAddEditScreen: () -> Unit
) {
    val uiState = viewModel.uiState
    val tasks by uiState.tasks.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { HomeScreenTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddEditScreen() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                Text(text = "Today")
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(tasks) { task ->
                TaskListItem(task = task, onTaskCheck = {})
            }
        }
    }
}