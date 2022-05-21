package com.son.finalproject.ui.authentication.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentSignUpBinding
import com.son.finalproject.ui.authentication.viewmodels.AuthenticationViewModel
import com.son.finalproject.utils.Validator
import com.son.finalproject.utils.Validator.isValid
import com.son.finalproject.utils.Validator.validate
import com.son.finalproject.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, AuthenticationViewModel>() {
    override val viewModel: AuthenticationViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner

            initValidation()
        }
    }

    private fun initValidation() {
        binding.apply {
            val listTextInput = listOf(
                txtUsername.validate(listOf(Validator::isEmailValid), true),
                txtPassword.validate(listOf(Validator::isPasswordValid), true),
                txtConfirmPassword.validate(isRequireEmptyCheck = true)
            )

            btnSignupSignup.setOnClickListener {
                if(listTextInput.isValid()){
                    viewModel.onClickSignUp()
                }
            }
        }
    }
}