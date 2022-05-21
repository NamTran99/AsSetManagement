package com.son.finalproject.ui.home.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentRequestBinding
import com.son.finalproject.ui.home.viewmodels.RequestViewModel

class RequestFragment : BaseFragment<FragmentRequestBinding, RequestViewModel>() {
    override val viewModel: RequestViewModel by viewModels()
    override val layoutId = R.layout.fragment_request
}