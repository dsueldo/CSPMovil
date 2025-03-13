package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.remotecsolutionsperu.cspmovil.domain.entities.user.UserProfile
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor() : CspAppViewModel() {

    private val _newsList = MutableStateFlow<List<String>>(emptyList())
    val newsList: StateFlow<List<String>> = _newsList

    val db = Firebase.firestore

    init {
        fetchNewsList()
    }

    private fun fetchNewsList() {
        viewModelScope.launch {
            db.collection("news")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val title = snapshot.documents.get(0).getString("title")
                        Log.d(TAG, "Current data: ${title}")

                        Log.d(TAG, "Current data: ${snapshot.documents}")
                    } else {
                        Log.d(TAG, "Current data: null")
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