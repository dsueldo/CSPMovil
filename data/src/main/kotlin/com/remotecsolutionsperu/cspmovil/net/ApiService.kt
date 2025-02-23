package com.remotecsolutionsperu.cspmovil.net

import com.remotecsolutionsperu.cspmovil.entities.user.UserProfile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/api/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<UserProfile>
}