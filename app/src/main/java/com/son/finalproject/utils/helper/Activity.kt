package com.son.finalproject.utils.helper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showToast(contentID: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, getString(contentID), duration).show()
}

fun AppCompatActivity.showToast(contentID: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, contentID, duration).show()
}