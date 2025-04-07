package com.remotecsolutionsperu.cspmovil.domain.repositories

import com.remotecsolutionsperu.cspmovil.domain.entities.news.News

interface NewsRepository {
    suspend fun getAllNews(): List<News>
    suspend fun getNewsDetail(newsId: String): News?
}