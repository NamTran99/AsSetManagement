package com.son.finalproject.ui.authentication.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentSplashBinding
import com.son.finalproject.ui.authentication.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, AuthenticationViewModel>() {
    override val viewModel: AuthenticationViewModel by activityViewModels()
    override val layoutId = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSigninSpalsh.setOnClickListener {
                viewModel.clearAllField()
                navigateToDestination(R.id.action_splashFragment_to_signInFragment)
            }

            btnSignupSplash.setOnClickListener {
                viewModel.clearAllField()
                navigateToDestination(R.id.action_splashFragment_to_signUpFragment)
            }
        }
    }
}