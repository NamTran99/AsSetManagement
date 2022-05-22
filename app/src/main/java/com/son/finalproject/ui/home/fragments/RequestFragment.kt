package com.son.finalproject.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentRequestBinding
import com.son.finalproject.ui.home.viewmodels.RequestViewModel
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, RequestViewModel>() {
    override val viewModel: RequestViewModel by viewModels()
    override val layoutId = R.layout.fragment_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtStateRequest.showPopUp(action = viewModel::actionItemSpinner)
    }
}