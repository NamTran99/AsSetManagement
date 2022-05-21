package com.son.finalproject.base

import androidx.room.*

@Dao
interface BaseDao<T> {
    // trả về Long: Số lượng data insert thành công
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data : T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data : T)

    @Update
    suspend fun update(data : T)

    @Delete
    suspend fun delete(data : T)

    @Delete
    suspend fun delete(vararg data : T)
}