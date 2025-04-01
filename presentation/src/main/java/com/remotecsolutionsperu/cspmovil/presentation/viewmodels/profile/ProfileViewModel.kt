package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.usecases.GetProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
) : CspAppViewModel() {

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        fetchUserProfileData()
    }

    private fun fetchUserProfileData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val profile = getProfileUseCase.invoke()
                _profileUiState.value = profile
                _uiState.value = true
                Log.d("ProfileViewModel", "profile: ${_profileUiState.value}")
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching profile data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshProfile() {
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                val profile = getProfileUseCase.invoke()
                _profileUiState.value = profile
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error"
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}