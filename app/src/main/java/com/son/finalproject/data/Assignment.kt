package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension

@Entity(
    tableName = RoomExtension.TABLE_ASSIGNMENT
    ,foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("staffCode"),
        childColumns = arrayOf("userCode"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Asset::class,
        parentColumns = arrayOf("assetCode"),
        childColumns = arrayOf("assetCode"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class Assignment(
    @PrimaryKey(autoGenerate = true)
    val assignmentID: Int? = null,
    var assetCode: String = "",
    var userCode: String = "",
    var assignedDate: String = "",
    val status: Int = 2, //state: 0: declined, state 1: completed, stated 2: waiting
    var user: User = User(),
    var asset: Asset = Asset(),
    var assignedBy : String = "",
    val notes: String = "",
)