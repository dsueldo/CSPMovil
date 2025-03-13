package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn

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

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onSignInClick() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                accountService.signIn(_email.value, _password.value)
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
}