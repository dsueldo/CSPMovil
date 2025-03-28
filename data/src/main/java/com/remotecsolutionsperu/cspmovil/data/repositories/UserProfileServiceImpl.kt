package com.remotecsolutionsperu.cspmovil.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserProfileServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserProfileService {

    override suspend fun getProfile(): ProfileUiState {
        val uid = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("Usuario no autenticado")
        val document = firestore.collection("profiles").document(uid).get().await()
        return document.toObject(ProfileUiState::class.java) ?: ProfileUiState()
    }

    override suspend fun saveProfile(profile: ProfileUiState) {
        val uid = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("Usuario no autenticado")
        firestore.collection("profiles").document(uid).set(profile).await()
    }
}