package com.remotecsolutionsperu.cspmovil.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.database
import com.remotecsolutionsperu.cspmovil.domain.entities.user.User
import com.remotecsolutionsperu.cspmovil.domain.repositories.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth
    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let {User(it.uid, it.email) })
                }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
        }

    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override suspend fun signIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
        Log.d("AccountServiceImpl", "User signed in successfully")
        Log.d("AccountServiceImpl", "User ID: ${Firebase.auth.currentUser!!.uid}")
        Log.d("AccountServiceImpl", "Email: $email")
        Log.d("AccountServiceImpl", "Password: $password")
        Log.d("AccountServiceImpl", "User: ${Firebase.auth.currentUser}")
        Log.d("AccountServiceImpl", "User UID: ${Firebase.auth.currentUser?.uid}")
        Log.d("AccountServiceImpl", "User Email: ${Firebase.auth.currentUser?.email}")
        Log.d("AccountServiceImpl", "User Display Name: ${Firebase.auth.currentUser?.displayName}")
        Log.d("AccountServiceImpl", "User Phone Number: ${Firebase.auth.currentUser?.tenantId}")

        val idToken = getIdToken()
        Log.d("AccountServiceImpl", "ID Token: $idToken")
    }

    override suspend fun signUp(email: String, password: String) {
        try {
            val userCredential = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val user = userCredential.user ?: throw Exception("User creation failed")

            val allowedUsersRef = Firebase.database.reference.child("allowed_user")
            val snapshot = allowedUsersRef.get().await()

            val genericTypeIndicator = object : GenericTypeIndicator<List<String>>() {}
            val allowedUsers = snapshot.getValue(genericTypeIndicator) ?: emptyList()

            if (allowedUsers.contains(email)) {
                val userRef = Firebase.database.reference.child("users").child(user.uid)
                userRef.setValue(mapOf("email" to email)).await()
                Log.d("AuthRepositoryImpl", "User allowed and saved: $email")
                Log.d("AccountServiceImpl", "User signUp in successfully")
                Log.d("AccountServiceImpl", "User ID: ${Firebase.auth.currentUser!!.uid}")
                Log.d("AccountServiceImpl", "Email: $email")
                Log.d("AccountServiceImpl", "Password: $password")
                Log.d("AccountServiceImpl", "User: ${Firebase.auth.currentUser}")
                Log.d("AccountServiceImpl", "User UID: ${Firebase.auth.currentUser?.uid}")
                Log.d("AccountServiceImpl", "User Email: ${Firebase.auth.currentUser?.email}")
                Log.d("AccountServiceImpl", "User Display Name: ${Firebase.auth.currentUser?.displayName}")
                Log.d("AccountServiceImpl", "User Phone Number: ${Firebase.auth.currentUser?.tenantId}")
            } else {
                user.delete().await()
                Log.d("AuthRepositoryImpl", "User not allowed and deleted: $email")
                throw Exception("Email is not allowed")
            }
        } catch (e: Exception) {
            Log.e("AuthRepositoryImpl", "Error during sign-up: ${e.message}")
            throw e
        }
    }

    override suspend fun sendEmailVerification() {
        Firebase.auth.currentUser?.sendEmailVerification()?.await()
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        Firebase.auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun signOut() {
        auth.signOut()
        Log.d("AccountServiceImpl", "User signed out successfully")
    }

    override suspend fun deleteAccount() {
        Firebase.auth.currentUser!!.delete().await()
    }

    override suspend fun getIdToken(): String? {
        return Firebase.auth.currentUser?.getIdToken(true)?.await()?.token
    }

    override suspend fun refreshToken(): String? {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun getToken(): String? {
        TODO("Not yet implemented")
    }

    override fun deleteToken() {
        TODO("Not yet implemented")
    }
}