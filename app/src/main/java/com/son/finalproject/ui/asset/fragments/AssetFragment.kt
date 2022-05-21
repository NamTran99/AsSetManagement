package com.son.finalproject.ui.asset.fragments

import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssetBinding
import com.son.finalproject.ui.asset.viewmodels.AssetViewModel

class AssetFragment : BaseFragment<FragmentAssetBinding, AssetViewModel>() {
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_asset
}