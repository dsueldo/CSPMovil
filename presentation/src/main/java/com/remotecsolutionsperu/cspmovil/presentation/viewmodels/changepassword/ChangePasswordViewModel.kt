package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.changepassword

import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val accountService: AccountService,
): CspAppViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _isEmailSent = MutableStateFlow(false)
    val isEmailSent: StateFlow<Boolean> = _isEmailSent

    fun sendPasswordResetEmail(email: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                accountService.sendPasswordResetEmail(email)
                _isEmailSent.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Failed to send password reset email. Please try again."
            } finally {
                _isLoading.value = false
            }
        }
    }
}