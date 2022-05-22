package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import android.util.Log
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(application: Application): BaseViewModel(application) {

    fun floatingActionButtonClick(){
        navigateToDestination(R.id.action_requestFragment_to_fragmentCreateRequest)
    }
    
    fun actionItemSpinner(itemId: Int){
        Log.d(TAG, "actionItemSpinner: $itemId")
    }
}