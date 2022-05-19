package com.son.finalproject.ui.authentication.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentSignInBinding
import com.son.finalproject.ui.authentication.viewmodels.AuthenticationViewModel
import com.son.finalproject.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding, AuthenticationViewModel>() {
    override val viewModel: AuthenticationViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner

            root.setOnClickListener{
                hideKeyboard(binding.edtPasswordLogin)
                hideKeyboard(binding.edtUsernameLogin)
            }
        }
    }
}