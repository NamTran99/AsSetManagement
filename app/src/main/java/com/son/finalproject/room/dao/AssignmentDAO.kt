package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.Category
import com.son.finalproject.data.User
import com.son.finalproject.utils.RoomExtension

@Dao
interface AssignmentDAO : BaseDao<Assignment> {
    @Query("select * from ${RoomExtension.TABLE_ASSIGNMENT}")
    suspend fun getAllAssignment() : List<Assignment>

    @Query("select * from ${RoomExtension.TABLE_ASSIGNMENT} where userCode = :userID")
    suspend fun getAssignmentByUserID(userID: String): List<Assignment>
}