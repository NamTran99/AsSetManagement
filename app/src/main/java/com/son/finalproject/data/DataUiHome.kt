package com.son.finalproject.data;

import com.son.finalproject.R


data class DataUiHome(
    var fullName: String = "",
    var dateTime: String = "",
    var requestPending: Int = 0,
    var requestAccept: Int = 0,
    var assetsAvaiable : Int = 0,
    var textBox1 : Int = R.string.assets,
    var textBox2 : Int = R.string.assignment,
    var textBox3 : Int = R.string.reports,
    var textBox4 : Int = R.string.requests,
    var textBtn : Int = R.string.create_new_asset
)
