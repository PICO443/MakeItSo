package com.pico.make_it_so.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val db: FirebaseFirestore, private val accountService: AccountService) : TaskRepository {
    override val tasks: Flow<List<Task>>
        get() {
            val query = db.collection("users/${accountService.currentUser?.uid}/tasks")
            return query.orderBy(Task::dueDate.name, Query.Direction.DESCENDING).snapshots().map {
                it.toObjects()
            }
        }

    override suspend fun addTask(task: Task) {
        db.collection("users/${accountService.currentUser?.uid}/tasks").add(task).await()
    }
}