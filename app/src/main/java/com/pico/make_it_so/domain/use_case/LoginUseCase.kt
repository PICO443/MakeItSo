package com.pico.make_it_so.domain.use_case

import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val accountService: AccountService) {
    suspend operator fun invoke(email: String, password: String){
        accountService.login(email, password)
    }
}