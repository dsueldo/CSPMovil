package com.remotecsolutionsperu.cspmovil.di

import com.remotecsolutionsperu.cspmovil.impl.AccountServiceImpl
import com.remotecsolutionsperu.cspmovil.net.AccountService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}