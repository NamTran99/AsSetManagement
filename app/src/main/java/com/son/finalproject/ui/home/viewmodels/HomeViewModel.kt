package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.DataUiHome
import com.son.finalproject.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepositoryImpl
) : BaseViewModel(application) {
    private val dataUi = DataUiHome()
    private val _homeData = MutableLiveData<DataUiHome>()
    val homeData get() = _homeData


    init {
        viewModelScope.launch {
            authRepository.getUserByEmail(mySharedPreferences.getUserEmail())?.let {
                dataUi.fullName = "${it.firstName} ${it.lastName}"
                if(it.type == TYPE_USER){
                    dataUi.textBox2 = R.string.profile
                    dataUi.textBtn = R.string.create_request
                }
            }
            dataUi.dateTime = SimpleDateFormat("E dd/MM/yyyy").format(Date())
            _homeData.postValue(dataUi)
        }
    }

    fun onClickCreateAsset() {
        navigateToDestination(R.id.action_homeFragment_to_createAssetFragment)
    }

    fun onClickAsset() {
        navigateToDestination(R.id.action_homeFragment_to_assetManageFragment)
    }

    fun onClickRequest(){
        navigateToDestination(R.id.action_homeFragment_to_requestFragment)
    }

    companion object{
        private const val TYPE_ADMIN = 1
        private const val TYPE_USER = 0
    }
}