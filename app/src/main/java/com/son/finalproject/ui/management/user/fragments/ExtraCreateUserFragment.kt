package com.son.finalproject.ui.management.user.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentExtraCreateUserBinding
import com.son.finalproject.ui.management.user.viewmodels.CreateUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExtraCreateUserFragment :
    BaseFragment<FragmentExtraCreateUserBinding, CreateUserViewModel>() {
    override val viewModel: CreateUserViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_extra_create_user

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}