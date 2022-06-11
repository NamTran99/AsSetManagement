package com.son.finalproject.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Extension{
    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getLocalDate(pattern: String = "dd/MM/yyyy"): String{
        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return currentDate.format(formatter)
    }
}
