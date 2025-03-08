package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signUp

import com.remotecsolutionsperu.cspmovil.presentation.navigation.NEWS_LIST_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SIGN_UP_SCREEN
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : CspAppViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _successMessage = MutableStateFlow("")
    val successMessage: StateFlow<String> = _successMessage

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value != confirmPassword.value) {
                throw Exception("Passwords do not match")
            }
            accountService.sendEmailVerification()
            _successMessage.value = "Verification email sent. Please check your inbox."

            accountService.signUp(email.value, password.value)
            openAndPopUp(NEWS_LIST_SCREEN, SIGN_UP_SCREEN)
        }
    }
}