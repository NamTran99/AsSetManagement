package com.son.finalproject.ui.management.user.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.User
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    private val _userFiler = mutableListOf<User>()
    val listFilterUser = MutableLiveData(_userFiler)

    private var filterUserType = FILTER_DEFAULT_TYPE_ID
    private var filterUserString = EMPTY_STRING

    init {
        viewModelScope.launch {
            listFilterUser.value = getAllUser().toMutableList()
        }
    }

    private suspend fun getAllUser() = manageRepositoryImpl.getAllUser()

    fun onClickCreateUser() {
        navigateToDestination(R.id.action_userManagementFragment_to_createUserFragment)
    }

    fun onClickSetUserType(userType: Int)= viewModelScope.launch {
        filterUserType = when (userType) {
            R.id.menu_admin -> 0
            R.id.menu_user -> 1
            else -> 2
        }

        filterUserType()
    }

    fun onSearchNameTextChange(text: String) = viewModelScope.launch {
        filterUserString = text
        filterUserType()
    }

    private fun filterUserType() = viewModelScope.launch {
        val allUser = getAllUser()
        listFilterUser.value = (if (filterUserType != FILTER_DEFAULT_TYPE_ID) {
            allUser.filter { user ->
                user.type == filterUserType
            }
        } else allUser).filter {
            it.fullName.contains(filterUserString) || it.staffCode.contains(filterUserString) ||
                    it.userName.contains(filterUserString)|| it.joinDate.contains(filterUserString)
        }.toMutableList()
    }

    fun removeUser(user: User)= viewModelScope.launch{
        if(manageRepositoryImpl.removeUser(user) > 0){
            showToast(R.string.remove_user_successfully)
            listFilterUser.value = getAllUser().toMutableList()
        }
    }

    companion object {
        const val FILTER_DEFAULT_TYPE_ID = 3 // all user
        const val EMPTY_STRING = ""
    }
}