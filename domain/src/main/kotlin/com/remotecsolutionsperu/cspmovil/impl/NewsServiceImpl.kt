package com.remotecsolutionsperu.cspmovil.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.remotecsolutionsperu.cspmovil.net.NewsService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NewsServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : NewsService {
    override suspend fun getNewsList(): List<String> {
        val snapshot = firestore.collection("news").get().await()
        return snapshot.documents.map { it.getString("newsTitle").orEmpty() }
    }
}