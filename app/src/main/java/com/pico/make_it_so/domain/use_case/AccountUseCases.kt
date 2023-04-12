package com.pico.make_it_so.domain.use_case

data class AccountUseCases(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val signUpUseCase: SignUpUseCase,
    val loginUseCase: LoginUseCase,
    val loginAnonymouslyUseCase: LoginAnonymouslyUseCase
)
