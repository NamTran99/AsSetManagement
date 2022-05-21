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

    val email = MutableLiveData(EMPTY_STRING)
    val password = MutableLiveData(EMPTY_STRING)
    val rewritePassword = MutableLiveData(EMPTY_STRING)

    fun autoLogin(){

    }

    fun loginUser() = viewModelScope.launch {
        email.value?.let {
            val user = authRepository.getUserByEmail(it)
            Log.d(TAG, "onClickLogin: $user")
            user?.let { user ->
                if (user.password == password.value) {
                    navigateToDestination(R.id.action_signInFragment_to_homeFragment)
                    mySharedPreferences.saveUserEmail(user.email)
                    showToast(getString(R.string.sign_in_succesfully))
                } else {
                    showToast(getString(R.string.sign_in_with_wrong_password))
                }
            }
        }
    }

    fun onClickSignUp() = viewModelScope.launch {
        email.value?.let { email ->
            password.value?.let{ password ->
                rewritePassword.value?.let{ rewritePassword ->
                    val isSuccess = authRepository.registerUser(User(email = email, password = password))
                    if(isSuccess > 0){
                        navigateToDestination(R.id.action_signUpFragment_to_homeFragment)
                        mySharedPreferences.saveUserEmail(email)
                        showToast(getString(R.string.sign_up_succesfully))
                    }
                }
            }
        }
    }

    fun clearAllField(){
        email.value = EMPTY_STRING
        password.value = EMPTY_STRING
        rewritePassword.value = EMPTY_STRING
    }

    companion object {
        const val EMPTY_STRING = ""
    }
}