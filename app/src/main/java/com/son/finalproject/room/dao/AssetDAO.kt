package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.utils.RoomExtension

@Dao
interface AssetDAO : BaseDao<Asset> {
    @Query("select count(*) from ${RoomExtension.TABLE_ASSET} where categoryID like :categoryID")
    suspend fun countItemByCategoryID(categoryID: Int): Int
}