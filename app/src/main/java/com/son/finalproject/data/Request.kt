package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension.TABLE_REQUEST

@Entity(
    tableName = TABLE_REQUEST,
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("staffCode"),
        childColumns = arrayOf("staffCode"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Request(
    @PrimaryKey
    val requestID: Int? = null,
    var staffCode: String = "",
    var requestDate: String= "",
    var status: Int = 0, // 0 requesting, 1 declined, 2 complete
    var assetCode: String= "", // mã hóa
    var asset: Asset = Asset(),
    var user: User = User()
)
