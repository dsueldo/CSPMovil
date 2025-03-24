package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn

import android.util.Log
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.remotecsolutionsperu.cspmovil.data.repositories.EmailValidationService
import com.remotecsolutionsperu.cspmovil.data.repositories.PasswordValidationService
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.utils.SessionManager
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    private val sessionManager: SessionManager,
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

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val emailValidationService = EmailValidationService()
    private val passwordValidationService = PasswordValidationService()


    fun resetState() {
        _uiState.value = false
        _errorMessage.value = ""
    }

    fun updateEmail(newEmail: TextFieldValue) {
        _email.value = newEmail
        _errorMessage.value = ""
    }

    fun updatePassword(newPassword: TextFieldValue) {
        _password.value = newPassword
        _errorMessage.value = ""
    }

    fun validateEnterEmail(): Boolean {
        val isValid = emailValidationService.validate(_email.value)
        if (!isValid) {
            _errorMessage.value = emailValidationService.getErrorMessage()
            Log.d("SignInViewModel", "Error: ${_errorMessage.value}")
        }
        return isValid
    }

    fun validateEnterPassword(): Boolean {
        val isValid = passwordValidationService.validate(_password.value)
        if (!isValid) {
            _errorMessage.value = passwordValidationService.getErrorMessage()
            Log.d("SignInViewModel", "Error: ${_errorMessage.value}")
        }
        return isValid
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
        if (!validateEnterEmail() || !validateEnterPassword()) return
        _isLoading.value = true
        viewModelScope.launch {
            try {
                accountService.signIn(_email.value.text, _password.value.text)
                val idToken = accountService.getIdToken()
                if (idToken != null) {
                    val expirationTime = System.currentTimeMillis() + TOKEN_EXPIRATION_DURATION
                    sessionManager.saveUserSession(
                        accountService.currentUserId,
                        idToken,
                        expirationTime
                    )
                }
                Log.d("SignInViewModel", "currentUserId: ${accountService.currentUserId}")
                Log.d("SignInViewModel", "User signed in getIdToken: ${accountService.getIdToken()}")
                _uiState.value = true
            } catch (e: Exception) {
                _uiState.value = false
                _errorMessage.value = when {
                    e.message?.contains("The supplied auth credential is incorrect, malformed or has expired.") == true -> {
                        "Credenciales incorrectas. Intente de nuevo."
                    }
                    e.message?.contains("Network error") == true -> {
                        "Error de conexión. Por favor, inténtelo de nuevo."
                    }
                    else -> "Ocurrió un error: ${e.message}"
                }
                Log.d("SignInViewModel", "Error: ${e.message}")
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

    companion object {
        private const val TOKEN_EXPIRATION_DURATION = 60000 // 1 min in milliseconds
    }
}