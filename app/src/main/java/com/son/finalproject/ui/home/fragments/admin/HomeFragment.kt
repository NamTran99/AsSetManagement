package com.son.finalproject.ui.home.fragments.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentHomeBinding
import com.son.finalproject.ui.home.viewmodels.HomeViewModel
import com.son.finalproject.ui.main.UserType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initObserve()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun initObserve() {
        viewModel.liveUserType.observe(viewLifecycleOwner){
            when(it){
                0 -> UserType.Amin
                1 -> UserType.User
                else -> UserType.User
            }.let{ userType ->
                setVisibilityForNavigationFollowUser(userType)
            }
        }
    }
}