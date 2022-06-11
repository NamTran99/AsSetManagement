package com.son.finalproject.room;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification
import com.son.finalproject.data.User

/*
 vì sqlite room chỉ nhận kiểu dữ liệu nguyên thủy không chấp nhận kiểu dữ liệu khác làm thành phần trong bảng
 nên phải convert kiểu dữ liệu dùng TypeConverter có sẵn trong room database kết hợp với thư viện Gson
 convert các kiểu dữ liệu khác ngoài (int, string, long,...)
*/
public class AppTypeConverter {
    @TypeConverter
    fun fromSpecificationToObject(value: Specification): String? {
        val gson = Gson()
        val type = object : TypeToken<Specification?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSpecificationToObject(value: String?): Specification? {
        val gson = Gson()
        val type = object : TypeToken<Specification?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun convertUserToObject(value: User): String? {
        val gson = Gson()
        val type = object : TypeToken<User?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun convertStringToUser(value: String?): User? {
        val gson = Gson()
        val type = object : TypeToken<User?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun convertAssetToObject(value: Asset): String? {
        val gson = Gson()
        val type = object : TypeToken<Asset?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun convertStringToAsset(value: String?): Asset? {
        val gson = Gson()
        val type = object : TypeToken<Asset?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun convertCategoryToObject(value: Category): String? {
        val gson = Gson()
        val type = object : TypeToken<Category?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun convertStringToCategory(value: String?): Category? {
        val gson = Gson()
        val type = object : TypeToken<Category?>() {}.type
        return gson.fromJson(value, type)
    }
}
