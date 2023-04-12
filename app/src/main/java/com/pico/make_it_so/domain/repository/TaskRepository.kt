package com.pico.make_it_so.domain.repository

import com.pico.make_it_so.domain.model.Task
import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    val tasks: Flow<List<Task>>
    suspend fun addTask(task: Task)
}