package com.remotecsolutionsperu.cspmovil.domain.repositories

interface NewsService {
    suspend fun getNewsList(): List<String>
}