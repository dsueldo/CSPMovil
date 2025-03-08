package com.remotecsolutionsperu.cspmovil.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsService
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