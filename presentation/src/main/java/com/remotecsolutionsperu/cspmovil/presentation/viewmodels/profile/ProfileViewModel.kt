package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : CspAppViewModel() {

    private val _profileData = MutableStateFlow<ProfileItem?>(null)
    val profileData: StateFlow<ProfileItem?> = _profileData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        val currentUser = auth.currentUser
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
        }
    }
}

data class ProfileItem(
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val gender: String,
    val birthday: String,
    val dni: String,
    val codeNumber: String,
    val council: String,
    val condition: String,
    val payLastPeriod: String,
)