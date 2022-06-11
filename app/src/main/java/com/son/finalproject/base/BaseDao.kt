package com.son.finalproject.base

import androidx.room.*

// interface base room DAO cho data base hỗ trợ thêm, xóa , sửa
@Dao
interface BaseDao<T> {
    // trả về Long: Số lượng data insert thành công
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data : T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data : T)

    @Update
    suspend fun update(data : T): Int

    @Delete
    suspend fun delete(data : T): Int

    @Delete
    suspend fun delete(vararg data : T)
}