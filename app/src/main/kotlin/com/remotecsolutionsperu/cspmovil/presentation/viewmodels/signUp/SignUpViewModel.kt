package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signUp

import com.remotecsolutionsperu.cspmovil.application.NEWS_LIST_SCREEN
import com.remotecsolutionsperu.cspmovil.application.SIGN_UP_SCREEN
import com.remotecsolutionsperu.cspmovil.net.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : CspAppViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value != confirmPassword.value) {
                throw Exception("Passwords do not match")
            }

            accountService.signUp(email.value, password.value)
            openAndPopUp(NEWS_LIST_SCREEN, SIGN_UP_SCREEN)
        }
    }
}