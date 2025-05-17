package com.remotecsolutionsperu.cspmovil.domain.usecases

import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val userProfileService: UserProfileService
) {
    suspend fun getProfileData(): ProfileUiState {
        return userProfileService.getProfile()
    }

    suspend fun saveProfileData(profile: ProfileUiState) {
        return userProfileService.saveProfile(profile)
    }
}