package com.remotecsolutionsperu.presentation.viewmodels.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.remotecsolutionsperu.presentation.entities.login.LoginRequest
import com.remotecsolutionsperu.presentation.repository.auth.AuthRepository
import com.remotecsolutionsperu.presentation.repository.auth.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    var loginUiState by mutableStateOf(LoginUiState())
        private set
    var customToken by mutableStateOf<String?>(null)
        private set
    var idToken by mutableStateOf<String?>(null)
        private set

    fun updateCode(username: String) {
        loginUiState = loginUiState.copy(code = username)
    }

    fun updatePassword(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun getCustomToken() {
        loginUiState = loginUiState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val request = LoginRequest(loginUiState.code, loginUiState.password)
            when (val result = authRepository.getCustomToken(request)) {
                is Result.Success -> {
                    customToken = result.data.token
                    customToken?.let { signInWithCustomToken(it) }
                    loginUiState = loginUiState.copy(isSuccess = true, isLoading = false)
                }

                is Result.Failure -> {
                    loginUiState =
                        loginUiState.copy(error = result.exception.message, isLoading = false)
                }
            }
        }
    }

    private fun signInWithCustomToken(token: String) {
        auth.signInWithCustomToken(token)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "signInWithCustomToken:success")
                    loginUiState = loginUiState.copy(isSuccess = true)
                    _authState.value = AuthState.Authenticated
                    getCurrentUserToken()
                } else {
                    // Sign in failed
                    Log.w("AuthViewModel", "signInWithCustomToken:failure", task.exception)
                    loginUiState = loginUiState.copy(error = task.exception?.message)
                    _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    private fun getCurrentUserToken() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        user?.getIdToken(true) // Force refresh if needed
            ?.addOnSuccessListener { result ->
                val idToken = result.token
                this.idToken = idToken
                // Save token securely
                saveTokenSecurely(idToken)
                Log.d("AuthViewModel", "getCurrentUserToken:success")
            }
            ?.addOnFailureListener { exception ->
                Log.e("AuthViewModel", "getCurrentUserToken:failure", exception)
                updateAuthState(AuthState.Unauthenticated)
            }
    }

    private fun saveTokenSecurely(token: String?) {
        // Implement secure token storage, e.g., using EncryptedSharedPreferences
//        token?.let {
//            SecureStorage.saveToken(context, it)
//        }
    }

    private fun updateAuthState(state: AuthState) {
        _authState.value = state
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

data class LoginUiState(
    val code: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)