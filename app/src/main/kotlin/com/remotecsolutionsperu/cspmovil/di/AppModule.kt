package com.remotecsolutionsperu.cspmovil.di

import com.remotecsolutionsperu.cspmovil.net.ApiService
import com.remotecsolutionsperu.cspmovil.repository.auth.SignRepository
import com.remotecsolutionsperu.cspmovil.repository.login.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSignRepository(): SignRepository {
        return SignRepository()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }
}