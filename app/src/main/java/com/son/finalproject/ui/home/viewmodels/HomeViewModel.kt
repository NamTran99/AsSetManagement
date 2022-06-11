package com.son.finalproject.ui.home.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.DataUiHome
import com.son.finalproject.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepositoryImpl
) : BaseViewModel(application) {
    private val dataUi = DataUiHome()
    private val _homeData = MutableLiveData<DataUiHome>()
    val homeData get() = _homeData
    val liveUserType = MutableLiveData(0)

    init {
        viewModelScope.launch {
            authRepository.getUserByEmail(mySharedPreferences.getUserEmail())?.let {
                dataUi.fullName = "${it.firstName} ${it.lastName}"
                if(it.type == TYPE_USER){
                    mySharedPreferences.saveIsAdmin(false)
                    dataUi.textBox2 = R.string.profile
                    dataUi.textBtn = R.string.create_request
                }else{
                    mySharedPreferences.saveIsAdmin(true)
                }
                liveUserType.value = it.type
            }
            dataUi.dateTime = SimpleDateFormat("E dd/MM/yyyy").format(Date())
            _homeData.postValue(dataUi)
        }
    }

    fun onClickCreateButton() = viewModelScope.launch {
        liveUserType.value?.let{
            if (it == TYPE_USER){
                navigateToDestination(R.id.action_homeFragment_to_fragmentCreateRequest)
            }else{
                navigateToDestination(R.id.action_homeFragment_to_createAssetFragment)
            }
        }
    }

    fun onClickAsset() {
        navigateToDestination(R.id.assetManageFragment)
    }

    fun onClickRequest(){
        navigateToDestination(R.id.requestManagementFragment)
    }

    companion object{
        private const val TYPE_ADMIN = 0
        private const val TYPE_USER = 1
    }
}