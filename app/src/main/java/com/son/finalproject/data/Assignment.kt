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
    var status: Int = 2, //state: 0: declined, state 1: completed, stated 2: waiting,3: returning, 4 returned
    var user: User = User(),
    var asset: Asset = Asset(),
    var assignedBy : String = "",
    val notes: String = "",
){
    companion object{
        const val STATE_DECLINED = 0
        const val STATE_COMPLETED = 1
        const val STATE_WAITING = 2
        const val STATE_RETURNING = 3
        const val STATE_RETURNED = 4
    }

    override fun hashCode(): Int {
        return assignmentID.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val otherItem  = other as? Assignment
        return status == otherItem?.status
    }
}