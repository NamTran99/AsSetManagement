package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Asset(
    val assetCode: Int,
    val assetName: String,
    val installDate: String,
    val specificationID: Int,
    val status: Int,
    val location: String,
    val isAssigned: Boolean,
    val categoryID: Int,
)