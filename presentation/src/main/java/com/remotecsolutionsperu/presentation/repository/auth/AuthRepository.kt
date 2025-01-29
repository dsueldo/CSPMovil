package com.remotecsolutionsperu.presentation.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.remotecsolutionsperu.presentation.entities.login.LoginRequest
import com.remotecsolutionsperu.presentation.entities.token.CustomTokenResponse
import com.remotecsolutionsperu.presentation.net.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCustomToken(
        request: LoginRequest
    ): Result<CustomTokenResponse> {
        return try {
            val response = apiService.getCustomToken(request)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Failure(
                    Exception(
                        "Failed to get custom token: ${response.errorBody()?.string()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}