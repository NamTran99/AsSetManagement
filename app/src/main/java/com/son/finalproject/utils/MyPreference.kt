package com.son.finalproject.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val TAG = "MyPreference"
        private const val SHARE_PREFERENCE_NAME = "share_preference_name"

        private const val USER_EMAIL = "user_email"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveUserEmail(email: String){
        Log.d(TAG, "saveUserEmail: $email ")
        put(USER_EMAIL, email)
    }

    fun getUserEmail(): String {
        Log.d(TAG, "getImageLink:${get(USER_EMAIL, String::class.java)} ")
        return get(USER_EMAIL, String::class.java)
    }

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()

        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    fun clear() {
        sharedPref.edit().run {
            remove(USER_EMAIL)
        }.apply()
    }
}