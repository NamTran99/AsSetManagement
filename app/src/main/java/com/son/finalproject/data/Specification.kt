package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Specification (
    @PrimaryKey
    val specificationID: Int,
    val specification: String,
    val modelNo: Int,
    val serialNo: String,
    val notes: String,
    val barcode: String
)