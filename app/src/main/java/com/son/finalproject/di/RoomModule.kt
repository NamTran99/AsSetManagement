package com.son.finalproject.di

import com.son.finalproject.data.Assignment
import com.son.finalproject.room.AppDataBase
import com.son.finalproject.room.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RoomModule {

    // tạo các DAO hỗ trợ lấy data trong room
    @Singleton
    @Provides
    fun provideMissionDAO(db: AppDataBase): UserDAO = db.userDAO()

    @Singleton
    @Provides
    fun provideAssetDAO(db: AppDataBase): AssetDAO = db.assetDAO()

    @Singleton
    @Provides
    fun provideCategoryDAO(db: AppDataBase): CategoryDAO = db.categoryDAO()

    @Singleton
    @Provides
    fun provideSpecificationDAO(db: AppDataBase): SpecificationDAO = db.specificationDAO()

    @Singleton
    @Provides
    fun provideAssignmentDAO(db: AppDataBase): AssignmentDAO = db.assignmentDAO()

    @Singleton
    @Provides
    fun provideRequestDAO(db: AppDataBase): RequestDAO = db.requestDAO()
}