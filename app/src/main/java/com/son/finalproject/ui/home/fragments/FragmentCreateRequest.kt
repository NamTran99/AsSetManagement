package com.son.finalproject.ui.home.fragments;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateRequestBinding
import com.son.finalproject.ui.home.viewmodels.CreateRequestViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/22/2022
*/
@AndroidEntryPoint
public class FragmentCreateRequest : BaseFragment<FragmentCreateRequestBinding,CreateRequestViewModel>() {
    override val viewModel: CreateRequestViewModel by viewModels()
    override val layoutId: Int get() = R.layout.fragment_create_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.action = viewModel
    }
}
