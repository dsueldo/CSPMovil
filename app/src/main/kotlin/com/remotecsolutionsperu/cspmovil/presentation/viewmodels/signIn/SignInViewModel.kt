package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn

import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.remotecsolutionsperu.cspmovil.application.NEWS_LIST_SCREEN
import com.remotecsolutionsperu.cspmovil.application.SIGN_IN_SCREEN
import com.remotecsolutionsperu.cspmovil.application.SIGN_UP_SCREEN
import com.remotecsolutionsperu.cspmovil.net.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : CspAppViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                accountService.signIn(email.value, password.value)
                openAndPopUp(NEWS_LIST_SCREEN, SIGN_IN_SCREEN)
            } catch (e: Exception) {
                _errorMessage.value = when (e) {
                    is FirebaseAuthInvalidUserException -> "No existe una cuenta relacionada con este email"
                    is FirebaseAuthInvalidCredentialsException -> "Credenciales incorrectas. Intente de nuevo"
                    is FirebaseNetworkException -> "Error de conexión. Por favor revise su conexión a internet "
                    else -> "An unknown error occurred. Please try again."
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
    }
}