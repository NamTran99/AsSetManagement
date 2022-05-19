package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Specification(
    val specificationID: Int,
    val specification: String,
    val modelNo: Int,
    val serialNo: String,
    val notes: String,
    val barcode: String
)