package com.remotecsolutionsperu.cspmovil.domain.repositories

import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState

interface UserProfileService {
    suspend fun getProfile(): ProfileUiState
    suspend fun saveProfile(profile: ProfileUiState)
}