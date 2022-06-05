package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Asset
import com.son.finalproject.data.User
import com.son.finalproject.utils.RoomExtension

@Dao
interface UserDAO : BaseDao<User>{

    @Query("SELECT * FROM ${RoomExtension.TABLE_USER} WHERE email LIKE :email")
    suspend fun getUserNameByEmail(email:String): User?

    @Query("SELECT staffCode FROM ${RoomExtension.TABLE_USER} order by staffCode DESC LIMIT 1")
    suspend fun getHighestUserID(): String?

    @Query("select * from ${RoomExtension.TABLE_USER}")
    suspend fun getListUser() : List<User>
}
