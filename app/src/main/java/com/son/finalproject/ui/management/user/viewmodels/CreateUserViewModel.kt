package com.son.finalproject.ui.management.user.viewmodels

import android.app.Application
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.User
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.ui.management.user.fragments.CreateUserFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    val liveUser = MutableLiveData(User())
    var nextUserID = "SD0001"

    fun getNextUserID() = viewModelScope.launch {
        val currentHighestCode = manageRepositoryImpl.getHighestUserID()

        currentHighestCode?.let {
            nextUserID = USER_ID_FORMAT + (it.drop(USER_ID_FORMAT_LENGTH).toInt() + 1).toString()
                .padStart(USER_ID_NUMBER, '0')
        }
        Log.d(TAG, "getNextUserID: $nextUserID")
        liveUser.value?.staffCode = nextUserID
    }

    fun onGenderChangeListener(genderID: Int) {
        liveUser.value?.gender = genderID
    }

    fun setDobUser(dateOfBirth: String) {
        liveUser.value?.dateOfBirth = dateOfBirth
    }

    fun setJoinDateTimeUser(dateTime: String) {
        liveUser.value?.joinDate = dateTime
    }

    fun onClickSetUserType(id: Int) {
        liveUser.value?.type = when (id) {
            R.id.menu_admin -> 0
            else -> 1
        }
    }
    // di chuyển sang màn hình nhập thêm thông tin cho user
    fun onClickNextInputUser() {
        navigateToDestination(
            R.id.action_createUserFragment_to_extraCreateUserFragment, bundleOf(
                CreateUserFragment.ARG_STAFF_CODE to mUserCode
            )
        )
    }
    // quay trở lại màn hình đầu
    fun onClickBackUser() {
        onBackStack()
    }

    fun onClickSaveUser() = viewModelScope.launch {
        if (!isEditType) {
            liveUser.value?.apply {
                fullName = "$firstName $lastName"
                if (manageRepositoryImpl.insertUser(this) >= 0) {
                    showToast(R.string.create_user_successfully)
                    onBackStack()
                    onBackStack()
                }
            }
        } else {
            liveUser.value?.apply {
                manageRepositoryImpl.updateUser(this)
                showToast(R.string.update_user_successfully)
                onBackStack()
                onBackStack()
            }
        }

    }

    var isEditType = false
    var mUserCode = ""

    fun setUserCode(userCode: String) = viewModelScope.launch {
        mUserCode = userCode
        isEditType = userCode.isNotEmpty()
        if (isEditType) {
            liveUser.value = getUserByID(userCode)
        }
    }

    private suspend fun getUserByID(userID: String) = manageRepositoryImpl.getUserByID(userID)

    companion object {
        const val USER_ID_FORMAT = "SD"
        const val USER_ID_FORMAT_LENGTH = 2
        const val USER_ID_NUMBER = 4
    }
}