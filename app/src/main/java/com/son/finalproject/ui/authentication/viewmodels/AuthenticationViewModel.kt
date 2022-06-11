package com.son.finalproject.ui.authentication.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.User
import com.son.finalproject.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepositoryImpl
) : BaseViewModel(application) {
    // giả data
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val rewritePassword = MutableLiveData(EMPTY_STRING)

    init {
       viewModelScope.launch {
           authRepository.getUserByEmail("")
       }
    }

    // đăng nhập
    fun loginUser() = viewModelScope.launch {
        email.value?.let {
            val user = authRepository.getUserByEmail(it)
            Log.d(TAG, "onClickLogin: $user")
            user?.let { user ->
                if (user.password == password.value) {
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
        email.value?.let { email ->
            password.value?.let{ password ->
                rewritePassword.value?.let{
                    val isSuccess = authRepository.registerUser(User(email = email, password = password))
                    if(isSuccess > ZERO){
                        navigateToDestination(R.id.action_signUpFragment_to_homeFragment)
                        mySharedPreferences.saveUserEmail(email)
                        showToast(getString(R.string.sign_up_succesfully))
                    }
                }
            }
        }
    }

    fun checkAccount() {
        if(mySharedPreferences.getUserEmail() != EMPTY_STRING){
            navigateToDestination(R.id.action_signInFragment_to_homeFragment)
        }
    }

    fun clearAllField(){
        email.value = EMPTY_STRING
        password.value = EMPTY_STRING
        rewritePassword.value = EMPTY_STRING
    }

    companion object {
        private const val ZERO = 0
        private const val EMPTY_STRING = ""
        private const val TYPE_ADMIN = 1
    }
}