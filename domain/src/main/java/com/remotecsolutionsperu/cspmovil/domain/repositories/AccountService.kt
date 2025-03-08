package com.remotecsolutionsperu.cspmovil.domain.repositories

import com.remotecsolutionsperu.cspmovil.domain.entities.user.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun sendEmailVerification()
    suspend fun signOut()
    suspend fun deleteAccount()
}