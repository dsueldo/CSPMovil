package com.remotecsolutionsperu.cspmovil.net

interface NewsService {
    suspend fun getNewsList(): List<String>
}