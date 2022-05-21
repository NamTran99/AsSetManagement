package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Request(
    val requestID: Int,
    val staffCode: Int,
    val requestDate: String,
    val status: Boolean = false, // false -> request true -> complex
    val requestedAsset: String // mã hóa
)
