package com.son.finalproject.room.dao;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Request
import com.son.finalproject.utils.RoomExtension

@Dao
interface RequestDAO: BaseDao<Request> {

    @Query("select * from ${RoomExtension.TABLE_REQUEST}")
    suspend fun getAllRequest(): List<Request>

    @Query("select * from ${RoomExtension.TABLE_REQUEST} where staffCode = :userID")
    suspend fun getAllRequestByUserID(userID: String): List<Request>

    @Query("select * from ${RoomExtension.TABLE_REQUEST} where staffCode = :userID")
    suspend fun getRequestByUserID(userID: String): List<Request>

    @Query("delete from ${RoomExtension.TABLE_REQUEST} where assetCode = :assetCode")
    suspend fun deleteRequestByAssetCode(assetCode: String)

    @Query("select * from ${RoomExtension.TABLE_REQUEST}  where assetCode = :assetCode")
    suspend fun getAllRequestByAssetCode(assetCode: String) : List<Request>
}