package com.son.finalproject.ui.home.viewmodels;

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.User
import com.son.finalproject.repository.AuthRepositoryImpl
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.repository.interfaces.ManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/22/2022
*/
@HiltViewModel
public class CreateRequestViewModel @Inject constructor(
    application: Application,
    private val repository: ManageRepositoryImpl,
    private val authRepository: AuthRepositoryImpl
) : BaseViewModel(application) {
    private val _listAsset = MutableLiveData<List<Asset>>()
    val listAsset : LiveData<List<Asset>> get() = _listAsset

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> get() = _user

    private var assetPosition : Asset? = null

    init {
        viewModelScope.launch {
            _listAsset.value = repository.getListAsset()

            authRepository.getUserByEmail(mySharedPreferences.getUserEmail())?.let {
                _user.value = it
            }
        }
    }

    fun onSelectedAssetSpinner(p2: Int) {
        listAsset.value?.let {
            assetPosition = it[p2]
        }
    }
}
