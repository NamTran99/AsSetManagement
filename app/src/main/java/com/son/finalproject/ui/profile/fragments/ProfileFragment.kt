package com.son.finalproject.ui.profile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentProfileBinding
import com.son.finalproject.ui.profile.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val layoutId = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initObserve()
    }

    private fun initObserve() {
        viewModel.liveUser.observe(viewLifecycleOwner){
            when(it.gender){
                0 -> binding.groupGender.check(binding.radioMale.id)
                1 -> binding.groupGender.check(binding.radioFemale.id)
                else -> binding.groupGender.check(binding.radioMale.id)
            }

            binding.edtUserType.text = if(it.type == 0) "Admin" else "User"
        }
    }


}