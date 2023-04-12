package com.pico.make_it_so.di

import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccountUseCases(accountService: AccountService): AccountUseCases {
        return AccountUseCases(
            signUpUseCase = SignUpUseCase(accountService),
            getCurrentUserUseCase = GetCurrentUserUseCase(accountService),
            loginUseCase = LoginUseCase(accountService),
            loginAnonymouslyUseCase = LoginAnonymouslyUseCase(accountService)
        )
    }
}