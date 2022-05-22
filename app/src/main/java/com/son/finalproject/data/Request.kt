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
    )]
)
data class Request(
    @PrimaryKey
    val requestID: Int,
    val staffCode: Int,
    val requestDate: String,
    val status: Boolean = false, // false -> request true -> complex
    val assetCode: String // mã hóa
)
