package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.son.finalproject.utils.Extension
import java.io.Serializable

@Entity
data class Specification (
    @PrimaryKey
    val specificationID: Int? = null,
    var specification: String = "",
    val modelNo: String = Extension.getRandomString(MODEL_LENGTH),
    val serialNo: String = Extension.getRandomString(SERIAL_LENGTH),
    val notes: String = "",
    val barcode: String = Extension.getRandomString(BARCODE_LENGTH)
){
    companion object{
        const val MODEL_LENGTH = 4
        const val SERIAL_LENGTH = 5
        const val BARCODE_LENGTH = 5
    }
}