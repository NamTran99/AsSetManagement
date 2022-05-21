package com.son.finalproject.ui.asset.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssetManagementBinding
import com.son.finalproject.ui.asset.viewmodels.AssetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetManagementFragment : BaseFragment<FragmentAssetManagementBinding, AssetViewModel>() {
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_asset_management

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
        }
    }
}