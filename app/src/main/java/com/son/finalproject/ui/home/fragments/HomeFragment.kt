package com.son.finalproject.ui.home.fragments

import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentHomeBinding
import com.son.finalproject.ui.home.viewmodels.HomeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId = R.layout.fragment_home
}