package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension
import java.io.Serializable

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
data class Asset(
    @PrimaryKey
    val assetCode: String = "",
    val assetName: String = "",
    val installDate: String = "",
    var specification: Specification? = null,
    val status: Int = 0, // 0 available, 1 notvailable
    val location: String = "",
    val isAssigned: Boolean = false,
    val categoryID: Int = 0,
)