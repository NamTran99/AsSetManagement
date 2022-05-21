package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension

@Entity(tableName = RoomExtension.TABLE_CATEGORY)
data class Category(
    @PrimaryKey
    val categoryID: Int = 0,
    val categoryName: String = "",
){
    companion object{
        val listCategory = listOf(
            Category(
                categoryID = 0,
                categoryName = "Laptop"
            ),
            Category(
                categoryID = 1,
                categoryName = "Mouse"
            ),
            Category(
                categoryID = 2,
                categoryName = "Personal Computer"
            ),
        )
    }
}