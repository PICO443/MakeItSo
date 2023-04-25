package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation._nav_graphs.TaskNavGraph
import com.pico.make_it_so.presentation.home.home.task.add_edit_task.components.AddEditTaskTopAppBar
import com.pico.make_it_so.presentation.home.home.task.add_edit_task.components.IconButtonOption
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@TaskNavGraph(start = true)
@Destination
@Composable
fun AddEditScreen(
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()

    fun getDate(timeMillis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val selectedDate = Calendar.getInstance()
        selectedDate.timeInMillis = timeMillis
        val todayDate = Calendar.getInstance()
        val tomorrowDate = Calendar.getInstance()
        tomorrowDate.add(Calendar.DATE, 1)
        return when (formatter.format(selectedDate.timeInMillis)) {
            formatter.format(todayDate.timeInMillis) -> {
                "Today"
            }
            formatter.format(tomorrowDate.timeInMillis) -> {
                "Tomorrow"
            }
            else -> {
                formatter.format(selectedDate.timeInMillis)
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    viewModel.onEvent(AddEditTaskEvent.SaveTask)
                    navigator.navigateUp()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.save_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
        },
        topBar = {
            AddEditTaskTopAppBar(navigateBack = { navigator.navigateUp() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            var openDatePicker by remember {
                mutableStateOf(false)
            }
            val datePickerState = rememberDatePickerState(uiState.taskDateMillis)

            if (openDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        openDatePicker = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            openDatePicker = false
                            coroutineScope.launch {
                                datePickerState.selectedDateMillis?.let {
                                    viewModel.onEvent(
                                        AddEditTaskEvent.OnTaskDateChange(it)
                                    )
                                }
                            }
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            openDatePicker = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
            TextField(
                value = uiState.taskTitle,
                placeholder = { Text(text = "Title") },
                onValueChange = {
                    coroutineScope.launch {
                        viewModel.onEvent(AddEditTaskEvent.OnTitleChange(it))
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.taskDescription,
                placeholder = { Text(text = "Description") },
                onValueChange = {
                    coroutineScope.launch {
                        viewModel.onEvent(AddEditTaskEvent.OnDescriptionChange(it))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            IconButtonOption(
                label = "Date",
                supportingText = getDate(uiState.taskDateMillis),
                icon = ImageVector.vectorResource(id = R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                onButtonClicked = {
                    openDatePicker = true
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreenPreview() {
    MakeItSoTheme {
        var openDialog by remember {
            mutableStateOf(true)
        }
        val datePickerState = rememberDatePickerState()
        if (openDialog)
            DatePickerDialog(onDismissRequest = {
                openDialog = false
            }, confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                    println("${datePickerState.selectedDateMillis}")
                }) {
                    Text(text = "OK")
                }
            }) {
                DatePicker(state = datePickerState)
            }
    }
}
