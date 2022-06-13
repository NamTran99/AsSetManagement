package com.son.finalproject.ui.authentication.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.User
import com.son.finalproject.repository.AuthRepositoryImpl
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.ui.management.user.viewmodels.CreateUserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepositoryImpl,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    val liveUser = MutableLiveData(User())
    val rewritePassword = MutableLiveData(EMPTY_STRING)

    init {
        viewModelScope.launch {
            authRepository.getUserByEmail("")
        }
    }

    // đăng nhập
    fun loginUser() = viewModelScope.launch {
        liveUser.value?.let {
            val user = authRepository.getUserByEmail(it.email)
            Log.d(TAG, "onClickLogin: $user")
            user?.let { user ->
                if (user.password == it.password) {
                    navigateToDestination(R.id.action_signInFragment_to_homeFragment)
                    mySharedPreferences.saveUserEmail(user.email)
                    mySharedPreferences.saveUserID(user.staffCode)
                    showToast(getString(R.string.sign_in_succesfully))
                } else {
                    showToast(getString(R.string.sign_in_with_wrong_password))
                }
                return@launch
            }
            showToast(getString(R.string.user_not_found))
        }
    }

    // Đăng ký
    fun onClickSignUp() = viewModelScope.launch {
        getNextUserID()
        liveUser.value?.let { user ->
            rewritePassword.value?.let {
                val isSuccess = authRepository.registerUser(user.apply { staffCode = nextUserID })
                if (isSuccess > ZERO) {
                    navigateToDestination(R.id.action_signUpFragment_to_homeFragment)
                    mySharedPreferences.saveUserEmail(user.email)
                    showToast(getString(R.string.sign_up_succesfully))
                }
            }
        }
    }

    // Kiểm tra đã đăng nhập tài khoản nào chưa nếu rồi thì di chuyển vào màn trong
    fun checkAccount() {
        if (mySharedPreferences.getUserEmail() != EMPTY_STRING) {
            navigateToDestination(R.id.action_signInFragment_to_homeFragment)
        }
    }

    // xóa data trong ô
    fun clearAllField() {
        liveUser.value = User()
        rewritePassword.value = EMPTY_STRING
    }

    var nextUserID = "SD0001"

    private fun getNextUserID() = viewModelScope.launch {
        val currentHighestCode = manageRepositoryImpl.getHighestUserID()

        currentHighestCode?.let {
            nextUserID =
                CreateUserViewModel.USER_ID_FORMAT + (it.drop(CreateUserViewModel.USER_ID_FORMAT_LENGTH)
                    .toInt() + 1).toString()
                    .padStart(CreateUserViewModel.USER_ID_NUMBER, '0')
        }
    }


    companion object {
        private const val ZERO = 0
        private const val EMPTY_STRING = ""
        private const val TYPE_ADMIN = 1
    }
}