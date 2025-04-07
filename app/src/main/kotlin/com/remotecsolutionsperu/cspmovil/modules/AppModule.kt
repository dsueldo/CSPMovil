package com.remotecsolutionsperu.cspmovil.modules

import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import com.remotecsolutionsperu.cspmovil.domain.usecases.EditProfileUseCase
import com.remotecsolutionsperu.cspmovil.domain.usecases.GetProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.editprofile.EditProfileViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.factories.NewsListViewModelFactory
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.detail.NewsDetailViewModel
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

    @Provides
    fun provideNewsListViewModel(repository: NewsRepository): NewsListViewModel = NewsListViewModel(repository)

    @Provides
    fun provideNewsDetailViewModel(repository: NewsRepository): NewsDetailViewModel = NewsDetailViewModel(repository)

    @Provides
    fun provideNewsListViewModelFactory(repository: NewsRepository): NewsListViewModelFactory = NewsListViewModelFactory(repository)
}