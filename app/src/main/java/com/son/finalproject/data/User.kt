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
                firstName = "Khanh",
                lastName = "Nguyen Dinh",
                fullName = "Nguyen Dinh Khanh",
                dateOfBirth = "10/10/1998",
                joinDate = "19/05/2022",
                gender = 0,
                userName = "KhanhKhanh",
                isDisabled = false,
                location = "HCM",
                type = 0,
                email = "hoasen@gmail.com",
                password = "admin123"
            ),
            User(
                staffCode = "SD0002",
                firstName = "Dat",
                lastName = "Nguyen Thanh",
                fullName = "Nguyen Thanh Dat",
                dateOfBirth = "23/12/1998",
                joinDate = "19/05/2022",
                gender = 0,
                userName = "Tinl",
                isDisabled = false,
                location = "Long An",
                type = 1,
                email = "dat.nt2455@sinhvien.hoasen.edu.vn",
                password = "Dat2312"
            ),
            User(
                staffCode = "SD0003",
                firstName = "Trung",
                lastName = "Nguyen Ba",
                fullName = "Nguyen Ba Trung",
                dateOfBirth = "06/11/1998",
                joinDate = "19/05/2022",
                userName = "Trung QL",
                gender = 0,
                isDisabled = false,
                location = "Lam Dong",
                type = 1,
                email = "nguyen.dk10101998@gmail.com",
                password = "12345678"
            ),
            User(
                staffCode = "SD0004",
                firstName = "Trung",
                lastName = "Nguyen Ba",
                fullName = "Nguyen Ba Trung",
                dateOfBirth = "06/11/1998",
                joinDate = "19/05/2022",
                userName = "Trung QL",
                gender = 0,
                isDisabled = false,
                location = "Lam Dong",
                type = 1,
                email = "abc@gmail.com",
                password = "12345678"
            )
        )
    }
}