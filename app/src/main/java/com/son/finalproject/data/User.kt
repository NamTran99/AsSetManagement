package com.son.finalproject.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.son.finalproject.utils.RoomExtension

// Tạo các NHIỀU trường nào là unique, ở đây là trường <email>, tableNem: tạo tên bảng
@Entity(indices = [Index(value = ["email"], unique = true)], tableName = RoomExtension.TABLE_USER)
data class User(
    @PrimaryKey
    var staffCode: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var fullName: String = "",
    var userName: String = "",
    var password: String = "" ,
    var dateOfBirth: String = "",
    var joinDate: String = "",
    var gender: Int = 0, // 0 nam, 1 nu , 2 other
    var isDisabled: Boolean = false, // false (con hoat dong), true (het hoat dong)
    var location: String = "",
    var type: Int = 1, // 0 admin, 1 normal user
    var email: String = "",
) {
    override fun hashCode(): Int {
        return staffCode.hashCode()
    }

    companion object {
        val listUserInit = listOf(
            User(
                staffCode = "SD0001",
                firstName = "Nam",
                lastName = "Tran Dinh",
                fullName = "Tran Dinh Nam",
                dateOfBirth = "06/11/1999",
                joinDate = "19/05/2022",
                gender = 0,
                userName = "HoangTuGio",
                isDisabled = false,
                location = "Quảng Bình",
                type = 0,
                email = "trandinhnam1199@gmail.com",
                password = "12345678"
            ),
            User(
                staffCode = "SD0002",
                firstName = "Sang",
                lastName = "Thai Ba",
                fullName = "Thai Ba Sang",
                dateOfBirth = "06/11/1999",
                joinDate = "19/05/2022",
                userName = "Ca ba sa",
                gender = 0,
                isDisabled = false,
                location = "Quảng Bình",
                type = 1,
                email = "sangtran@gmail.com",
                password = "12345678"
            )
        )
    }
}