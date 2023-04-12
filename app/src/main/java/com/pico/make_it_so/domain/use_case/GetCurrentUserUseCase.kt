package com.pico.make_it_so.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val accountService: AccountService) {
    operator fun invoke(): FirebaseUser? {
        return accountService.currentUser
    }
}