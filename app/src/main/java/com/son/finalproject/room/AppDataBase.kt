package com.son.finalproject.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.User
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.UserDAO

@Database(entities = [User::class, Asset::class, Category::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase()
{
    abstract fun userDAO(): UserDAO
    abstract fun assetDAO(): AssetDAO
    abstract fun categoryDAO(): CategoryDAO
}