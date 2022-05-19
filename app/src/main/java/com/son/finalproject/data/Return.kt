package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Return(
    val returnID: Int,
    val assignmentID: Int,
    val requestedByID: Int,
    val returnDate: String,
    val status: Int, // cần làm rõ
    val assignedBy: Int,
)