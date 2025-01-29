package com.remotecsolutionsperu.presentation.repository.login

import com.remotecsolutionsperu.presentation.entities.login.LoginRequest
import com.remotecsolutionsperu.presentation.entities.login.LoginResponse
import com.remotecsolutionsperu.presentation.entities.user.UserProfile
import com.remotecsolutionsperu.presentation.net.ApiService
import com.remotecsolutionsperu.presentation.repository.auth.Result
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserProfile(token: String): Result<UserProfile> {
        return try {
            val response = apiService.getUserProfile(token)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Failure(
                    Exception(
                        "Failed to get user profile: ${
                            response.errorBody()?.string()
                        }"
                    )
                )
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    suspend fun getLogin(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = apiService.getLogin(request)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Failure(Exception("Failed to get login: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}