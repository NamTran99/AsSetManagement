package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension

@Entity(indices = [Index(value = ["email"], unique = true)], tableName = RoomExtension.TABLE_USER)
data class User(
    @PrimaryKey(autoGenerate = true)
    val staffCode: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val password: String,
    val dateOfBirth: String = "",
    val joinDate: String = "",
    val gender: Int = 2, // 0 nam, 1 nu , 2 other
    val isDisabled: Boolean = false, // false (con hoat dong), true (het hoat dong)
    val location: String = "",
    val type: Int = 1, // 0 admin, 1 normal user
    val email: String
) {
    companion object {
        val listUserInit = listOf(
            User(
                firstName = "Nam",
                lastName = "Tran Dinh",
                dateOfBirth = "06/11/1999",
                joinDate = "19/05/2022",
                gender = 0,
                isDisabled = false,
                location = "Quảng Bình",
                type = 0,
                email = "trandinhnam1199@gmail.com",
                password = "1"
            ),
            User(
                firstName = "Sang",
                lastName = "Thai Ba",
                dateOfBirth = "06/11/1999",
                joinDate = "19/05/2022",
                gender = 0,
                isDisabled = false,
                location = "Quảng Bình",
                type = 1,
                email = "sangtran@gmail.com",
                password = "1"
            )
        )
    }
}