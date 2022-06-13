package com.son.finalproject.ui.profile.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.User
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    init {
        viewModelScope.launch {
            liveUser.value = getUserByID(mySharedPreferences.getUserID())
        }
    }

    val liveUser = MutableLiveData(User())

    private suspend fun getUserByID(userID: String) = manageRepositoryImpl.getUserByID(userID)
}