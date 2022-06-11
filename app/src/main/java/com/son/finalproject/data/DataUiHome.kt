package com.son.finalproject.data;

import com.son.finalproject.R


data class DataUiHome(
    var fullName: String = "",
    var dateTime: String = "",
    var requestPending: Int = 0,
    var requestAccept: Int = 0,
    var assetsAvaiable : Int = 0,
    var textBox1 : String = "Assets",
    var content1: String = "",
    var textBox2 : String = "Users",
    var content2: String = "",
    var textBox3 : String = "Requests",
    var content3: String = "",
    var textBox4 : String = "Assignments",
    var content4: String = "",
    var textBtn : Int = R.string.create_new_asset
)
