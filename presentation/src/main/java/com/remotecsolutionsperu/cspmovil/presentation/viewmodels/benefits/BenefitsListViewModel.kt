package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.remotecsolutionsperu.cspmovil.domain.entities.benefits.BenefitsItem
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitsListViewModel @Inject constructor() : CspAppViewModel() {

    private val _benefitsList = MutableStateFlow<List<BenefitsItem>>(emptyList())
    val benefitsList: StateFlow<List<BenefitsItem>> = _benefitsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val db = Firebase.firestore

    init {
        fetchBenefitsList()
    }

    private fun fetchBenefitsList() {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("benefits")
                .get()
                .addOnSuccessListener { snapshot ->
                    val benefitsItems = snapshot.documents.mapNotNull { document ->
                        val image = document.getString("image")
                        val title = document.getString("title")
                        val content = document.getString("content")
                        val order = document.getLong("order")?.toInt()
                        Log.d(TAG, "Current data Image: $image")
                        Log.d(TAG, "Current data Title: $title")
                        Log.d(TAG, "Current data Content: $content")
                        Log.d(TAG, "Current data Order: $order")
                        if (image != null && title != null && content != null && order != null) {
                            BenefitsItem(image, title, content, order)
                        } else {
                            null
                        }
                    }.sortedBy { it.order }
                    _benefitsList.value = benefitsItems
                    _uiState.value = true
                    _isLoading.value = false
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error fetching benefits", e)
                    _errorMessage.value = "Error fetching benefits: ${e.message}"
                    _uiState.value = false
                    _isLoading.value = false
                }
        }
    }

    fun refreshBenefitsList() {
        _isRefreshing.value = true
        viewModelScope.launch {
            db.collection("benefits")
                .get()
                .addOnSuccessListener { snapshot ->
                    val benefitsItems = snapshot.documents.mapNotNull { document ->
                        val image = document.getString("image")
                        val title = document.getString("title")
                        val content = document.getString("content")
                        val order = document.getLong("order")?.toInt()
                        Log.d(TAG, "Current data Image: $image")
                        Log.d(TAG, "Current data Title: $title")
                        Log.d(TAG, "Current data Content: $content")
                        Log.d(TAG, "Current data Order: $order")
                        if (image != null && title != null && content != null && order != null) {
                            BenefitsItem(image, title, content, order)
                        } else {
                            null
                        }
                    }.sortedBy { it.order }
                    _benefitsList.value = benefitsItems
                    _uiState.value = true
                    _isLoading.value = false
                    _isRefreshing.value = false
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error fetching benefits", e)
                    _errorMessage.value = "Error fetching benefits: ${e.message}"
                    _uiState.value = false
                    _isLoading.value = false
                    _isRefreshing.value = false
                }
        }
    }
}