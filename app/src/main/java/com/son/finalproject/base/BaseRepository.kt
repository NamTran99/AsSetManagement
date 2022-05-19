package com.son.finalproject.base

import com.son.finalproject.utils.ToastManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    private val repository = ToastManager.getInstance()

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                repository.showErrorThrowable(throwable)
                Result.failure(throwable)
            }
        }
    }
}
