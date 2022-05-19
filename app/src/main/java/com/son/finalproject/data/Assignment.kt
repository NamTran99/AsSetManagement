package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Assignment(
    val assignmentID: Int,
    val assetCode: Int,
    val adminCode: Int,
    val staffCode: Int,
    val assignedDate: String,
    val status: Int,
    val notes: String,
)