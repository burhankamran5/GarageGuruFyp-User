package com.bkcoding.garagegurufyp_user.di

import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepository
import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesFirebaseAuthRepository(repo: AuthRepositoryImpl): AuthRepository
}