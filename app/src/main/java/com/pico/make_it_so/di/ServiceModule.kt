package com.pico.make_it_so.di

import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.data.firebase.services.authentication.AccountServiceImpl
import com.pico.make_it_so.data.repository.TaskRepositoryImpl
import com.pico.make_it_so.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}