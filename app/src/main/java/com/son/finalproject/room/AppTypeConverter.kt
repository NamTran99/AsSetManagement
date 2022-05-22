package com.son.finalproject.room;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.son.finalproject.data.Specification

/*
 vì sqlite room chỉ nhận kiểu dữ liệu nguyên thủy không chấp nhận kiểu dữ liệu khác làm thành phần trong bảng
 nên phải convert kiểu dữ liệu dùng TypeConverter có sẵn trong room database kết hợp với thư viện Gson
 convert các kiểu dữ liệu khác ngoài (int, string, long,...)
*/
public class AppTypeConverter {
    @TypeConverter
    fun fromSpecificationToObjec(value: Specification): String? {
        val gson = Gson()
        val type = object : TypeToken<Specification?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSpecificationToObjec(value: String?): Specification? {
        val gson = Gson()
        val type = object : TypeToken<Specification?>() {}.type
        return gson.fromJson(value, type)
    }
}
