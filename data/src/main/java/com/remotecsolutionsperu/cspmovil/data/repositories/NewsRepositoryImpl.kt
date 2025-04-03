package com.remotecsolutionsperu.cspmovil.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.remotecsolutionsperu.cspmovil.domain.entities.news.NewsItem
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : NewsRepository {

    override suspend fun getNewsList(): Result<List<NewsItem>> {
        val snapshot = firestore.collection("news").get().await()
        return try {
            val newsItems = snapshot.documents.mapNotNull { document ->
                val image = document.getString("image")
                val title = document.getString("title")
                val content = document.getString("content")
                val order = document.getLong("order")?.toInt()
                if (image != null && title != null && content != null && order != null) {
                    NewsItem(image, title, content, order)
                } else {
                    null
                }
            }
            Result.success(newsItems)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}