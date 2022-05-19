package com.son.finalproject.data

import androidx.room.Entity

@Entity
data class Category(
    val categoryID: Int,
    val categoryName: String,
)