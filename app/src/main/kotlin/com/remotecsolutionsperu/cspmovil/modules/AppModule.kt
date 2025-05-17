package com.remotecsolutionsperu.cspmovil.modules

import com.remotecsolutionsperu.cspmovil.domain.repositories.BenefitsRepository
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import com.remotecsolutionsperu.cspmovil.domain.usecases.ProfileUseCase
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits.BenefitsListViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits.detail.BenefitsDetailViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.editprofile.EditProfileViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.factories.BenefitsListViewModelFactory
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
    fun provideProfileUseCase(repository: UserProfileService): ProfileUseCase = ProfileUseCase(repository)

    @Provides
    fun provideEditProfileViewModel(profileUseCase: ProfileUseCase,): EditProfileViewModel = EditProfileViewModel(profileUseCase)

    @Provides
    fun provideNewsListViewModel(repository: NewsRepository): NewsListViewModel = NewsListViewModel(repository)

    @Provides
    fun provideNewsDetailViewModel(repository: NewsRepository): NewsDetailViewModel = NewsDetailViewModel(repository)

    @Provides
    fun provideNewsListViewModelFactory(repository: NewsRepository): NewsListViewModelFactory = NewsListViewModelFactory(repository)

    @Provides
    fun provideBenefitsListViewModel(repository: BenefitsRepository): BenefitsListViewModel = BenefitsListViewModel(repository)

    @Provides
    fun provideBenefitsDetailViewModel(repository: BenefitsRepository): BenefitsDetailViewModel = BenefitsDetailViewModel(repository)

    @Provides
    fun provideBenefitsListViewModelFactory(repository: BenefitsRepository): BenefitsListViewModelFactory = BenefitsListViewModelFactory(repository)
}