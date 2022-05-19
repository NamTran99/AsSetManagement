package com.son.finalproject.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.son.finalproject.data.User
import com.son.finalproject.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {

        return Room.databaseBuilder(
            context, AppDataBase::class.java,
            AppDataBase::class.java.simpleName
        ).addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("TAG", "onCreate: init DataBase")
                    CoroutineScope(Dispatchers.IO).launch {
                        provideAppDatabase(context).userDAO()
                            .insertAllMission(User.listUserInit)
                    }
                }
            }
        ).build()
    }
}