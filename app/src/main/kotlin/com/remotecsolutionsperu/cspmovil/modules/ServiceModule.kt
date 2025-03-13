package com.remotecsolutionsperu.cspmovil.modules

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
    abstract fun provideAccountService(accountServiceImpl: AccountServiceImpl): AccountService

    @Binds
    abstract fun bindNewsService(newsServiceImpl: NewsServiceImpl): NewsService
}