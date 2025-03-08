package com.remotecsolutionsperu.cspmovil.di.modules

import com.remotecsolutionsperu.cspmovil.data.repositories.AccountServiceImpl
import com.remotecsolutionsperu.cspmovil.data.repositories.NewsServiceImpl
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideNewsService(impl: NewsServiceImpl): NewsService
}