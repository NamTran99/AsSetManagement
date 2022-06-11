package com.son.finalproject.ui.management.request.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.AssetStatus
import com.son.finalproject.data.Request
import com.son.finalproject.data.User
import com.son.finalproject.repository.AuthRepositoryImpl
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRequestViewModel @Inject constructor(
    application: Application, private val repository: ManageRepositoryImpl,
    private val authRepository: AuthRepositoryImpl
) : BaseViewModel(application) {

    private val _listAsset = MutableLiveData<List<Asset>>()
    val listAsset: LiveData<List<Asset>> get() = _listAsset

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val currentRequest = Request()

    private var assetPosition: Asset? = null

    init {
        viewModelScope.launch {

            authRepository.getUserByEmail(mySharedPreferences.getUserEmail())?.let {
                _user.value = it
                currentRequest.user = it
                currentRequest.staffCode = it.staffCode
                _listAsset.value = repository.getAssetForRequest(it.staffCode)
            }
        }
    }

    fun setDateTimeRequest(dateTime: String){
        currentRequest.requestDate = dateTime
    }

    fun onClickSaveButton() = viewModelScope.launch{
        if(repository.insertRequest(currentRequest) >= 0){
            navigateToDestination(R.id.requestManagementFragment)
            showToast(R.string.create_request_successfully)
        }
    }

    fun onClickCancelButton(){
        onBackStack()
    }

    fun onSelectedAssetSpinner(position: Int) {
        listAsset.value?.let {
            currentRequest.asset = it[position]
            currentRequest.assetCode = it[position].assetCode
        }
    }
}