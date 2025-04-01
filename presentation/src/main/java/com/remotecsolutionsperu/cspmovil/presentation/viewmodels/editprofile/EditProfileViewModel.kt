package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.editprofile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.usecases.EditProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
) : CspAppViewModel() {

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun resetState() {
        _uiState.value = false
        _errorMessage.value = ""
    }

    fun updateName(name: String) {
        _profileUiState.value = _profileUiState.value.copy(name = name)
    }

    fun updateLastName(lastName: String) {
        _profileUiState.value = _profileUiState.value.copy(lastName = lastName)
    }

    fun updateEmail(email: String) {
        _profileUiState.value = _profileUiState.value.copy(email = email)
    }

    fun updatePhoneNumber(phone: String) {
        _profileUiState.value = _profileUiState.value.copy(phone = phone)
    }

    fun updateBirthday(birthday: String) {
        _profileUiState.value = _profileUiState.value.copy(birthday = birthday)
    }

    fun updateGender(gender: String) {
        _profileUiState.value = _profileUiState.value.copy(gender = gender)
    }

    fun updateDni(dni: String) {
        _profileUiState.value = _profileUiState.value.copy(dni = dni)
    }

    fun updateCodeNumber(codeNumber: String) {
        _profileUiState.value = _profileUiState.value.copy(codeNumber = codeNumber)
    }

    fun updateSpecialized(specialized: String) {
        _profileUiState.value = _profileUiState.value.copy(specialized = specialized)
    }

    fun validateFields(): Boolean {
        return _profileUiState.value.name.isNotEmpty() &&
                _profileUiState.value.lastName.isNotEmpty() &&
                _profileUiState.value.email.isNotEmpty() &&
                _profileUiState.value.phone.isNotEmpty() &&
                _profileUiState.value.birthday.isNotEmpty() &&
                _profileUiState.value.gender.isNotEmpty() &&
                _profileUiState.value.codeNumber.isNotEmpty()
    }

    fun saveProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                editProfileUseCase.invoke(_profileUiState.value)
                _uiState.value = true
                Log.d("EditProfileViewModel", "profile: ${_profileUiState.value}")
            } catch (e: Exception) {
                _uiState.value = false
                _errorMessage.value = e.message ?: "Error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}