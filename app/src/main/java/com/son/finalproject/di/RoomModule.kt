package com.son.finalproject.di

import com.son.finalproject.room.AppDataBase
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideMissionDAO(db: AppDataBase): UserDAO = db.userDAO()

    @Singleton
    @Provides
    fun provideAssetDAO(db: AppDataBase): AssetDAO = db.assetDAO()

    @Singleton
    @Provides
    fun provideCategoryDAO(db: AppDataBase): CategoryDAO = db.categoryDAO()
}