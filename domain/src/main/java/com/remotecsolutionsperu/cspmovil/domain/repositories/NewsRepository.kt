package com.remotecsolutionsperu.cspmovil.domain.repositories

interface NewsRepository {
    suspend fun getNewsList(): List<String>
}