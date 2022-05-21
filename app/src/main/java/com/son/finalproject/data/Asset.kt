package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension

@Entity(tableName = RoomExtension.TABLE_ASSET)
data class Asset(
    @PrimaryKey
    val assetCode: String = "",
    val assetName: String = "" ,
    val installDate: String= "",
    val specification: String= "",
    val status: Int = 0, // 0 available, 1 notvailable
    val location: String= "",
    val isAssigned: Boolean= false,
    val categoryID: Int = 0,
)