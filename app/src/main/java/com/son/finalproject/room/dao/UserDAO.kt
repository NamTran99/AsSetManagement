package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.son.finalproject.data.User
import com.son.finalproject.utils.RoomExtension

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(mission: User): Long

    @Insert
    suspend fun insertAllMission(liMission: List<User>)

    @Query("SELECT * FROM ${RoomExtension.TABLE_USER} WHERE email LIKE :email")
    suspend fun getUserNameByEmail(email:String): User?
}
