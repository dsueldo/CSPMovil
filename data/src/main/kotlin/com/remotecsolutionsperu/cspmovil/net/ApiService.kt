package com.remotecsolutionsperu.cspmovil.net

import com.remotecsolutionsperu.cspmovil.entities.login.LoginResponse
import com.remotecsolutionsperu.cspmovil.entities.login.LoginRequest
import com.remotecsolutionsperu.cspmovil.entities.token.CustomTokenResponse
import com.remotecsolutionsperu.cspmovil.entities.user.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/customToken")
    suspend fun getCustomToken(
        @Body request: LoginRequest
    ): Response<CustomTokenResponse>

    @POST("/api/login/")
    suspend fun getLogin(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("/api/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<UserProfile>
}