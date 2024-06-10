package com.bkcoding.garagegurufyp_user.di

import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepository
import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepositoryImpl
import com.bkcoding.garagegurufyp_user.repository.chat.ChatRepository
import com.bkcoding.garagegurufyp_user.repository.chat.ChatRepositoryImpl
import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepositoryImpl
import com.bkcoding.garagegurufyp_user.repository.user.UserRepository
import com.bkcoding.garagegurufyp_user.repository.user.UserRepositoryImpl
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
    @Binds
    @Singleton
    abstract fun providesUserRepository(repo: UserRepositoryImpl): UserRepository
    @Binds
    @Singleton
    abstract fun providesFcmRepository(repo: FcmRepositoryImpl): FcmRepository
    @Binds
    @Singleton
    abstract fun providesChatRepository(repo: ChatRepositoryImpl): ChatRepository
}