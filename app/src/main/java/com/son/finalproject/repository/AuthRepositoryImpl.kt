package com.son.finalproject.repository

import android.util.Log
import com.son.finalproject.data.User
import com.son.finalproject.repository.interfaces.AuthRepository
import com.son.finalproject.room.dao.UserDAO
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val userDao:UserDAO): AuthRepository() {
    override suspend fun getUserByEmail(email: String): User? {
        Log.d("TAG", "getUserByEmail: $email")
        return userDao.getUserNameByEmail(email)
    }

    override suspend fun registerUser(user: User): Long{
        Log.d("TAG", "registerUser: $user")
        return userDao.insert(user)
    }
}