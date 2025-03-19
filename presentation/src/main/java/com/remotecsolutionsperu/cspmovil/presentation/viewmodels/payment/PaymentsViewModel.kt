package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.payment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor() : CspAppViewModel() {

    private val _paymentsList = MutableStateFlow<List<PaymentsItem>>(emptyList())
    val paymentsList: StateFlow<List<PaymentsItem>> = _paymentsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    private val db = Firebase.firestore

    init {
        fetchPaymentsList()
    }

    private fun fetchPaymentsList() {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("payments")
                .get()
                .addOnSuccessListener { snapshot ->
                    val paymentsItems = snapshot.documents.mapNotNull { document ->
                        val image = document.getString("image")
                        val title = document.getString("title")
                        val content = document.getString("content")
                        val order = document.getLong("order")?.toInt()
                        Log.d(TAG, "Current data Image: $image")
                        Log.d(TAG, "Current data Title: $title")
                        Log.d(TAG, "Current data Content: $content")
                        if (image != null && title != null && content != null && order != null) {
                            PaymentsItem(image, title, content, order)
                        } else {
                            null
                        }
                    }.sortedBy { it.order }
                    _paymentsList.value = paymentsItems
                    _uiState.value = true
                    _isLoading.value = false
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error fetching payments", e)
                    _errorMessage.value = "Error fetching payments: ${e.message}"
                    _uiState.value = false
                    _isLoading.value = false
                }
        }
    }
}

data class PaymentsItem(
    val image: String,
    val title: String,
    val content: String,
    val order: Int,
)