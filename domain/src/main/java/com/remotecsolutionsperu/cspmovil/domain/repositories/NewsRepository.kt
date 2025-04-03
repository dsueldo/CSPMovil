package com.remotecsolutionsperu.cspmovil.domain.repositories

import com.remotecsolutionsperu.cspmovil.domain.entities.news.NewsItem

interface NewsRepository {
    suspend fun getNewsList(): Result<List<NewsItem>>
}