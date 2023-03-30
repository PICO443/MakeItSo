package com.pico.make_it_so.domain.use_case

import com.pico.make_it_so.data.firebase.services.authentication.AccountService
import javax.inject.Inject

class SignUpUseCase @Inject constructor (private val accountService: AccountService) {

    suspend operator fun invoke(email: String, password: String){
        if(email.isNotBlank().and(password.isNotBlank())){
            accountService.linkAccount(email, password)
        }
    }
}