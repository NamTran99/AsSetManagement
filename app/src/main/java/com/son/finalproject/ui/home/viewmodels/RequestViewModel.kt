package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import com.son.finalproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(application: Application): BaseViewModel(application) {
}