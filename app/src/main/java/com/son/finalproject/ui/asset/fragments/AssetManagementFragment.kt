package com.son.finalproject.ui.asset.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.base.WindowRotateType
import com.son.finalproject.databinding.FragmentAssetManagementBinding
import com.son.finalproject.ui.asset.adapter.AssetAdapter
import com.son.finalproject.ui.asset.viewmodels.AssetManagementViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetManagementFragment :
    BaseFragment<FragmentAssetManagementBinding, AssetManagementViewModel>() {
    override val viewModel: AssetManagementViewModel by viewModels()
    override val layoutId = R.layout.fragment_asset_management
    private val adapter = AssetAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerAsset.adapter = adapter
        }

        initObserver()
    }

    override fun onResume() {
//        rotateWindow(WindowRotateType.Horizontal)
        super.onResume() 
    }

    override fun onStop() {
//        rotateWindow(WindowRotateType.Vertical)
        super.onStop()
    }

    private fun initObserver() {
        viewModel.pairAssetAndCategory.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
    }
}