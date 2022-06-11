package com.son.finalproject.base

import com.son.finalproject.ui.main.UserType


interface IActivityApplication{
    fun rotateWindow(windowRotateType: WindowRotateType)
    fun setVisibilityForNavigationFollowUser(user: UserType)
}

// xoay màn hình
enum class WindowRotateType{
    Horizontal,
    Vertical
}