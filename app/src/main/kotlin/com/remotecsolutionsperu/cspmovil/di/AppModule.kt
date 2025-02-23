package com.remotecsolutionsperu.cspmovil.di

import com.remotecsolutionsperu.cspmovil.net.ApiService
import com.remotecsolutionsperu.cspmovil.repository.auth.AuthRepository
import com.remotecsolutionsperu.cspmovil.repository.login.LoginRepository
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
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): LoginRepository {
        return LoginRepository(apiService)
    }
}