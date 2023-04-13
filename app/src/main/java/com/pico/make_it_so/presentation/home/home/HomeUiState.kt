package com.pico.make_it_so.presentation.home.home

import com.pico.make_it_so.domain.model.Task
import kotlinx.coroutines.flow.Flow

data class HomeUiState(val tasks: Flow<List<Task>>)
