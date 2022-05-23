package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.utils.RoomExtension

@Dao
interface AssetDAO : BaseDao<Asset> {
    @Query("select assetCode from ${RoomExtension.TABLE_ASSET} where categoryID like :categoryID order by assetCode DESC")
    suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String>

    @Query("select * from ${RoomExtension.TABLE_ASSET}")
    suspend fun getListAsset() : List<Asset>
}