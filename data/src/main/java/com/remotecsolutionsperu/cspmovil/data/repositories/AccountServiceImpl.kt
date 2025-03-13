package com.remotecsolutionsperu.cspmovil.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.domain.entities.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid) })
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
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        Log.d("AccountServiceImpl", "User signUp in successfully")
        Log.d("AccountServiceImpl", "User ID: ${Firebase.auth.currentUser!!.uid}")
        Log.d("AccountServiceImpl", "Email: $email")
        Log.d("AccountServiceImpl", "Password: $password")
        Log.d("AccountServiceImpl", "User: ${Firebase.auth.currentUser}")
        Log.d("AccountServiceImpl", "User UID: ${Firebase.auth.currentUser?.uid}")
        Log.d("AccountServiceImpl", "User Email: ${Firebase.auth.currentUser?.email}")
        Log.d("AccountServiceImpl", "User Display Name: ${Firebase.auth.currentUser?.displayName}")
        Log.d("AccountServiceImpl", "User Phone Number: ${Firebase.auth.currentUser?.tenantId}")
    }

    override suspend fun sendEmailVerification() {
        Firebase.auth.currentUser?.sendEmailVerification()?.await()
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun deleteAccount() {
        Firebase.auth.currentUser!!.delete().await()
    }

    private suspend fun getIdToken(): String? {
        return Firebase.auth.currentUser?.getIdToken(true)?.await()?.token
    }
}
