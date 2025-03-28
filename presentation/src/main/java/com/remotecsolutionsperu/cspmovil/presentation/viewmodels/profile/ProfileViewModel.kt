package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.domain.usecases.GetProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
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

    fun resetState() {
        _uiState.value = false
        _errorMessage.value = ""
    }

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

    fun signOut() {
        viewModelScope.launch {
            try {
                accountService.signOut()
            } catch (e: Exception) {
                _errorMessage.value = "Error signing out: ${e.message}"
            }
        }
    }
        /*val currentUser = auth.currentUser
        if (currentUser != null) {
            val userUid = currentUser.uid
            _isLoading.value = true
            viewModelScope.launch {
                db.collection("profiles").document(userUid)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            val data = document.data
                            val profileItem = data?.let {
                                val name = it["name"] as? String
                                val lastName = it["lastName"] as? String
                                val phone = it["phone"] as? String
                                val email = it["email"] as? String
                                val gender = it["gender"] as? String
                                val birthday = it["birthday"] as? String
                                val dni = it["dni"] as? String
                                val codeNumber = it["codeNumber"] as? String
                                val council = it["council"] as? String
                                val condition = it["condition"] as? String
                                val payLastPeriod = it["payLastPeriod"] as? String
                                Log.d(TAG, "Current data Name: $name")
                                Log.d(TAG, "Current data lastName: $lastName")
                                Log.d(TAG, "Current data phone: $phone")
                                Log.d(TAG, "Current data email: $email")
                                Log.d(TAG, "Current data gender: $gender")
                                Log.d(TAG, "Current data birthday: $birthday")
                                Log.d(TAG, "Current data dni: $dni")
                                Log.d(TAG, "Current data codeNumber: $codeNumber")
                                Log.d(TAG, "Current data council: $council")
                                Log.d(TAG, "Current data condition: $condition")
                                Log.d(TAG, "Current data payLastPeriod: $payLastPeriod")
                                if (name != null && lastName != null && phone != null && email != null && gender != null && birthday != null && dni != null && codeNumber != null && council != null && condition != null && payLastPeriod != null) {
                                    ProfileItem(
                                        name,
                                        lastName,
                                        phone,
                                        email,
                                        gender,
                                        birthday,
                                        dni,
                                        codeNumber,
                                        council,
                                        condition,
                                        payLastPeriod
                                    )
                                } else {
                                    null
                                }
                            }
                            _profileData.value = profileItem
                            _uiState.value = true
                            _isLoading.value = false
                        } else {
                            _errorMessage.value = "Profile data not found"
                            _uiState.value = false
                        }
                        _isLoading.value = false
                    }
                    .addOnFailureListener { e ->
                        _errorMessage.value = "Error fetching profile data: ${e.message}"
                        _uiState.value = false
                        _isLoading.value = false
                    }
            }
        } else {
            _errorMessage.value = "User not authenticated"
            _uiState.value = false
            _isLoading.value = false
        }*/
}