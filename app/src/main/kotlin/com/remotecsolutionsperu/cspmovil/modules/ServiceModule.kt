package com.remotecsolutionsperu.cspmovil.modules

import com.remotecsolutionsperu.cspmovil.data.repositories.AccountServiceImpl
import com.remotecsolutionsperu.cspmovil.data.repositories.BenefitsRepositoryImpl
import com.remotecsolutionsperu.cspmovil.data.repositories.NewsRepositoryImpl
import com.remotecsolutionsperu.cspmovil.data.repositories.UserProfileServiceImpl
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.domain.repositories.BenefitsRepository
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import com.remotecsolutionsperu.cspmovil.domain.repositories.UserProfileService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(accountServiceImpl: AccountServiceImpl): AccountService

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindBenefitsRepository(benefitsRepositoryImpl: BenefitsRepositoryImpl): BenefitsRepository

    @Binds
    abstract fun provideUserProvideService(userProfileServiceImpl: UserProfileServiceImpl): UserProfileService
}