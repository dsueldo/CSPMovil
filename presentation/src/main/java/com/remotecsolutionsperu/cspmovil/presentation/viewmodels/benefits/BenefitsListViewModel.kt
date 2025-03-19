package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits

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
class BenefitsListViewModel @Inject constructor() : CspAppViewModel() {

    private val _benefitsList = MutableStateFlow<List<BenefitsItem>>(emptyList())
    val benefitsList : StateFlow<List<BenefitsItem>> = _benefitsList

    private val db = Firebase.firestore

    init {
        fetchBenefitsList()
    }

    private fun fetchBenefitsList() {
        viewModelScope.launch {
            db.collection("benefits")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val benefitsItems = snapshot.documents.mapNotNull { document ->
                            val image = document.getString("image")
                            val title = document.getString("title")
                            val content = document.getString("content")
                            val order = document.getLong("order")?.toInt()
                            Log.d(TAG, "Current data Image: $image")
                            Log.d(TAG, "Current data Title: $title")
                            Log.d(TAG, "Current data Content: $content")
                            if (image != null && title != null && content != null && order != null) {
                                BenefitsItem(image, title, content, order)
                            } else {
                                null
                            }
                        }.sortedBy { it.order }
                        _benefitsList.value = benefitsItems
                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }
        }
    }
}

data class BenefitsItem(
    val image: String,
    val title: String,
    val content: String,
    val order: Int,
) {
    companion object {
        fun empty() = BenefitsItem("", "", "", 0)
    }
}