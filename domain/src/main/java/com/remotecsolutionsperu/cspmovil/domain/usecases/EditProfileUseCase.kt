package com.remotecsolutionsperu.cspmovil.domain.usecases

import com.remotecsolutionsperu.cspmovil.domain.entities.user.ProfileUiState
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val userProfileService: UserProfileService,
) {
    suspend operator fun invoke(profile: ProfileUiState) {
        userProfileService.saveProfile(profile)
    }
}