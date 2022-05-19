package com.son.finalproject.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.son.finalproject.data.User
import com.son.finalproject.room.dao.UserDAO

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase()
{
    abstract fun userDAO(): UserDAO
}