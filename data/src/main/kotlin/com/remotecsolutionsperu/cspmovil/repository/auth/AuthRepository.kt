package com.remotecsolutionsperu.cspmovil.repository.auth

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.remotecsolutionsperu.cspmovil.entities.login.LoginRequest
import com.remotecsolutionsperu.cspmovil.entities.token.CustomTokenResponse
import com.remotecsolutionsperu.cspmovil.net.ApiService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    private val firebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("RestrictedApi")
    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    @SuppressLint("RestrictedApi")
    suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

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