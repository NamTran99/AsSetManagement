package com.son.finalproject.ui.home.viewmodels;

import android.app.Application
import com.son.finalproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/22/2022
*/
@HiltViewModel
public class CreateRequestViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
}
