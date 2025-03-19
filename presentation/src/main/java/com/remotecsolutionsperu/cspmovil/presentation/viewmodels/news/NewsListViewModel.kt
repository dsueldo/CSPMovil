package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.remotecsolutionsperu.cspmovil.domain.entities.user.UserProfile
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor() : CspAppViewModel() {

    private val _newsList = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsList: StateFlow<List<NewsItem>> = _newsList

    private val db = Firebase.firestore

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
                        val newsItems = snapshot.documents.mapNotNull { document ->
                            val image = document.getString("image")
                            val title = document.getString("title")
                            val content = document.getString("content")
                            Log.d(TAG, "Current data Image: $image")
                            Log.d(TAG, "Current data Title: $title")
                            Log.d(TAG, "Current data Content: $content")
                            if (image != null && title != null && content != null) {
                                NewsItem(image, title, content)
                            } else {
                                null
                            }
                        }
                        _newsList.value = newsItems
                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }
        }
    }
}

data class NewsItem(
    val image: String,
    val title: String,
    val content: String
)