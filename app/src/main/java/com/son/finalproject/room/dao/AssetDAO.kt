package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.utils.RoomExtension

// Các hàm xử lý để truy cập table
@Dao
interface AssetDAO : BaseDao<Asset> {
    @Query("select assetCode from ${RoomExtension.TABLE_ASSET} where categoryID like :categoryID order by assetCode DESC")
    suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String>

    @Query("select * from ${RoomExtension.TABLE_ASSET}")
    suspend fun getListAsset() : List<Asset>

    @Query("select * from ${RoomExtension.TABLE_ASSET} where status like :status order by assetCode DESC")
    suspend fun getAssetByStatus(status: Int): List<Asset>

    @Query("select assetCode from ${RoomExtension.TABLE_ASSET}")
    suspend fun getAllAssetCode() : List<String>

    @Query("select * from ${RoomExtension.TABLE_ASSET} where assetCode like :assetID")
    suspend fun getAssetByID(assetID: String) : Asset

    @Query("select * from asset where not exists (select * from ${RoomExtension.TABLE_REQUEST} where request.staffCode = :staffCode and asset.assetCode = request.assetCode) and status like :status ")
    suspend fun getAssetAvailableForRequest(staffCode: String, status: Int = 0): List<Asset>

}