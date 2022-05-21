package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel

class HomeViewModel(application: Application): BaseViewModel(application) {

    fun onClickCreateAsset(){
        navigateToDestination(R.id.action_homeFragment_to_createAssetFragment)
    }
}