package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Assignment::class,
        parentColumns = arrayOf("assignmentID"),
        childColumns = arrayOf("assignmentID"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Return(
    @PrimaryKey
    val returnID: Int,
    val assignmentID: Int,
    val requestedByID: Int,
    val returnDate: String,
    val status: Int, // cần làm rõ
    val assignedBy: Int,
)