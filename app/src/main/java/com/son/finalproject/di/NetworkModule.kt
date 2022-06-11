package com.son.finalproject.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.son.finalproject.data.Category
import com.son.finalproject.data.User
import com.son.finalproject.room.AppDataBase
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    // khởi tạo database toàn app
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        userDAO: Provider<UserDAO>,
        categoryDAO: Provider<CategoryDAO>
        ): AppDataBase {

        return Room.databaseBuilder(
            context, AppDataBase::class.java,
            AppDataBase::class.java.simpleName
        ).addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("TAG", "onCreate: init DataBase")
                    CoroutineScope(Dispatchers.IO).launch {
                        userDAO.get()
                            .insert(*User.listUserInit.toTypedArray())
                        categoryDAO.get().insert(
                            *Category.listCategory.toTypedArray()
                        )
                    }
                }
            }
        ).build()
    }
}