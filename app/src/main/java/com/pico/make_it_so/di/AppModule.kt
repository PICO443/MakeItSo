package com.pico.make_it_so.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import com.pico.make_it_so.domain.use_case.*
import com.pico.make_it_so.presentation.auth.google_sign_in.GoogleAuthUiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(@ApplicationContext context: Context): GoogleAuthUiClient {
        return GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
}