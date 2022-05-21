package com.son.finalproject.ui.authentication.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentSignInBinding
import com.son.finalproject.ui.authentication.viewmodels.AuthenticationViewModel
import com.son.finalproject.utils.Validator
import com.son.finalproject.utils.Validator.isEmailValid
import com.son.finalproject.utils.Validator.isPasswordValid
import com.son.finalproject.utils.Validator.isValid
import com.son.finalproject.utils.Validator.validate
import com.son.finalproject.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding, AuthenticationViewModel>() {
    override val viewModel: AuthenticationViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkAccount()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initValidation()
    }

    private fun initValidation() {
        binding.apply {
            val listTextInput = listOf(
                txtUsername.validate(listOf(::isEmailValid), true),
                txtPassword.validate(listOf(::isPasswordValid), true)
            )

            btnSigninLogin.setOnClickListener {
                Log.d(TAG, "initValidation: onclick")
                if(listTextInput.isValid()){
                    viewModel.loginUser()
                }
            }
        }
    }
}