package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("staffCode"),
        childColumns = arrayOf("staffCode"),
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
    @PrimaryKey
    val assignmentID: Int,
    val assetCode: Int,
    val adminCode: Int,
    val staffCode: Int,
    val assignedDate: String,
    val status: Int,
    val notes: String,
)