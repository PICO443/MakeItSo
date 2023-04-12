package com.pico.make_it_so.presentation.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation.Screen
import com.pico.make_it_so.presentation.add_edit_task.components.AddEditTaskTopAppBar
import com.pico.make_it_so.presentation.add_edit_task.components.Option

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState = viewModel.uiState
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditTaskEvent.SaveTask); navigateToHome() }) {
                Icon(
                    painter = painterResource(id = R.drawable.save_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
        },
        topBar = {
            AddEditTaskTopAppBar(navigateBack = navigateBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                value = uiState.taskTitle,
                placeholder = { Text(text = "Title") },
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.TitleChanged(it)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.taskDescription,
                placeholder = { Text(text = "Description") },
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.DescriptionChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            Option(
                label = "Date",
                supportingText = "2023/04/04",
                icon = ImageVector.vectorResource(id = R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                onButtonClicked = { /*TODO*/ })
            Option(
                label = "Duration",
                supportingText = "02:00",
                icon = ImageVector.vectorResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz24),
                onButtonClicked = { /*TODO*/ })
        }

    }
}
