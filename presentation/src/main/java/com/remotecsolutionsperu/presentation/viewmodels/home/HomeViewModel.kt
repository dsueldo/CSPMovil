package com.remotecsolutionsperu.presentation.viewmodels.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.presentation.entities.user.UserProfile
import com.remotecsolutionsperu.presentation.repository.auth.Result
import com.remotecsolutionsperu.presentation.repository.login.LoginRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModel() {

    var userProfileUiState by mutableStateOf(UserProfileUiState())
        private set

    fun getUserProfile(token: String) {
        userProfileUiState = userProfileUiState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = repository.getUserProfile(token)) {
                is Result.Success -> {
                    userProfileUiState = userProfileUiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        userProfile = result.data
                    )
                }
                is Result.Failure -> {
                    userProfileUiState = userProfileUiState.copy(
                        error = result.exception.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class UserProfileUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val userProfile: UserProfile? = null
)