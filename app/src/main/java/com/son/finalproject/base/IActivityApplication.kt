package com.son.finalproject.base


interface IActivityApplication{
    fun rotateWindow(windowRotateType: WindowRotateType)
}

enum class WindowRotateType{
    Horizontal,
    Vertical
}