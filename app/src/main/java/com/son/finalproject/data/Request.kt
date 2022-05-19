package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Request(
    val requestID: Int,
    val staffCode: Int,
    val requestDate: String,
    val status: Int, // cần làm rõ
    val requestedAsset: String // mã hóa
)
