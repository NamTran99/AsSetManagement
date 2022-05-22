package com.son.finalproject.data.joindata

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification
import com.son.finalproject.utils.RoomExtension

@Entity(
    tableName = RoomExtension.TABLE_ASSET,
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("categoryID"),
        childColumns = arrayOf("categoryID"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class AssetAndCategory(
    @PrimaryKey
    var assetCode: String = "",
    var assetName: String = "",
    var installDate: String = "",
    var specification: Specification = Specification(),
    var status: Int = 0, // 0 available, 1 not available
    var location: String = "",
    var isAssigned: Boolean = false,
    var categoryID: Int = 0,
)