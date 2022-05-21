package com.son.finalproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.son.finalproject.base.BaseDao
import com.son.finalproject.data.Category
import com.son.finalproject.utils.RoomExtension

@Dao
interface CategoryDAO: BaseDao<Category>{
    @Query("Select categoryName from ${RoomExtension.TABLE_CATEGORY} Where categoryName Like '%'||(:label)||'%' " +
            "order by categoryName ASC")
    suspend fun findAllCategoryContainWithSymBol(label: String): List<String>

    @Query("Select * from ${RoomExtension.TABLE_CATEGORY}")
    suspend fun getListCategoryName(): List<Category>
}