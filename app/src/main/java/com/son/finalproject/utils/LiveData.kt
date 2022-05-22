package com.son.finalproject.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.forceRefresh() {
    this.value = this.value
}
