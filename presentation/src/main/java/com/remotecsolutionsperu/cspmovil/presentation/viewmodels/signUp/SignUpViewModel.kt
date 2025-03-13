package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signUp

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.presentation.navigation.NEWS_LIST_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SIGN_UP_SCREEN
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        _errorMessage.value = ""
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        _errorMessage.value = ""
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
        _errorMessage.value = ""
    }

    private fun validatePasswords(): Boolean {
        return _password.value == _confirmPassword.value
    }

    fun validatePasswordStrength(password: String): String {
        return when {
            password.length < 6 -> "Contraseña es muy corta"
            !password.any { it.isDigit() } -> "Contraseña debe contener almenos un dígito"
            !password.any { it.isUpperCase() } -> "Password must contain at least one uppercase letter"
            !password.any { it.isLowerCase() } -> "Password must contain at least one lowercase letter"
            !password.any { !it.isLetterOrDigit() } -> "Password must contain at least one special character"
            else -> "Contraseña es fuerte"
        }
    }

    fun onSignUpClick() {
        val passwordStrengthMessage = validatePasswordStrength(_password.value)
        if (passwordStrengthMessage != "Contraseña es fuerte") {
            _errorMessage.value = passwordStrengthMessage
            return
        }
        if (_email.value.isEmpty()) {
            _errorMessage.value = "Ingrese el correo electrónico."
            return
        }
        if (_password.value.isEmpty()) {
            _errorMessage.value = "Ingrese la contraseña."
            return
        }
        if (!validatePasswords()) {
            _errorMessage.value = "Ingrese la contraseña correctamente."
            return
        }

        _isLoading.value = true
        viewModelScope.launch {
            try {
//                val isValidCode = accountService.validateCollegiateCode(_email.value, _collegiateCode.value)
//                if (!isValidCode) {
//                    _errorMessage.value = "Invalid collegiate code."
//                    return@launch
//                }
                accountService.signUp(email = _email.value, password = _password.value)
                _uiState.value = true
            } catch (e: Exception) {
                _uiState.value = false
//                _errorMessage.value = "An error occurred: ${e.message}"
                _errorMessage.value = if (e.message?.contains("The email address is already in use by another account") == true ) {
                    "EL correo ya se encuentra registrado."
                } else {
                    "An error occurred: ${e.message}"
                }
                Log.d("SignUpViewModel", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetState() {
        _uiState.value = false
        _errorMessage.value = ""
    }
}