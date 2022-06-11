package com.son.finalproject.data

data class Report(
    var category: String = "",
    var total: Int = 1,
    var assigned: Int = 0,
    var available: Int = 0,
    var notAvailable: Int = 0
)