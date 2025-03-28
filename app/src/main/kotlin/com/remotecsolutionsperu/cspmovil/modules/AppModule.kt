package com.remotecsolutionsperu.cspmovil.modules

import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import com.remotecsolutionsperu.cspmovil.domain.usecases.EditProfileUseCase
import com.remotecsolutionsperu.cspmovil.domain.usecases.GetProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.editprofile.EditProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetProfileUseCase(repository: UserProfileService): GetProfileUseCase = GetProfileUseCase(repository)

    @Provides
    fun provideEditProfileUseCase(repository: UserProfileService): EditProfileUseCase = EditProfileUseCase(repository)

    @Provides
    fun provideEditProfileViewModel(editProfileUseCase: EditProfileUseCase): EditProfileViewModel = EditProfileViewModel(editProfileUseCase)
}