package com.remotecsolutionsperu.cspmovil.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.remotecsolutionsperu.cspmovil.domain.entities.news.News
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getAllNews(): List<News> {
        return try {
            firestore.collection("news")
                .orderBy("order", Query.Direction.ASCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { document ->
                document.toObject(News::class.java)?.apply {
                    id = document.id
                    Log.d(TAG, "Current data image: $image")
                    Log.d(TAG, "Current data title: $title")
                    Log.d(TAG, "Current data content: $content")
                    Log.d(TAG, "Current data description: $description")
                }
            }
        } catch (e: Exception) {
            println("Error fetching all news: ${e.localizedMessage}")
            emptyList()
        }
    }

    override suspend fun getNewsDetail(newsId: String): News? {
        return try {
            firestore.collection("news")
                .document(newsId)
                .get()
                .await()
                .toObject(News::class.java)
        } catch (e: Exception) {
            println("Error fetching news detail for $newsId: ${e.localizedMessage}")
            null
        }
    }
}