package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
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

    private val _email = MutableStateFlow(TextFieldValue(""))
    val email: StateFlow<TextFieldValue> = _email

    private val _password = MutableStateFlow(TextFieldValue(""))
    val password: StateFlow<TextFieldValue> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    fun updateEmail(newEmail: TextFieldValue) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: TextFieldValue) {
        _password.value = newPassword
    }

    fun validatePasswordStrength(password: String): String {
        return when {
            password.length < 6 -> "La contraseña es muy corta"
            !password.any { it.isDigit() } -> "La contraseña debe contener al menos un dígito"
            !password.any { it.isUpperCase() } -> "La contraseña debe contener al menos una letra mayúscula"
            !password.any { it.isLowerCase() } -> "La contraseña debe contener al menos una letra minúscula"
            !password.any { !it.isLetterOrDigit() } -> "La contraseña debe contener al menos un carácter especial"
            else -> "La contraseña es fuerte"
        }
    }

    fun onSignInClick() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                accountService.signIn(_email.value.text, _password.value.text)
                _uiState.value = true
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

    fun clearUserData() {
        _email.value = TextFieldValue("")
        _password.value = TextFieldValue("")
        _errorMessage.value = ""
        _uiState.value = false
    }
}