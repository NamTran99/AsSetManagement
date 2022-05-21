package com.son.finalproject.room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.son.finalproject.data.*
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.UserDAO

@Database(entities = [User::class, Asset::class, Category::class,Request::class,Specification::class,Return::class,Assignment::class], version = 1, exportSchema = false)
@TypeConverters(AppTypeConverter::class)
abstract class AppDataBase: RoomDatabase()
{
    abstract fun userDAO(): UserDAO
    abstract fun assetDAO(): AssetDAO
    abstract fun categoryDAO(): CategoryDAO
}