package com.son.finalproject.repository.interfaces

import com.son.finalproject.data.User

abstract class AuthRepository {
    abstract suspend fun getUserByEmail(email: String) : User?
    abstract suspend fun registerUser(user: User) : Long
}