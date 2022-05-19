package com.son.finalproject.di

import com.son.finalproject.repository.AuthRepositoryImpl
import com.son.finalproject.room.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(userDao: UserDAO) = AuthRepositoryImpl(userDao)
}