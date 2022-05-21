package com.son.finalproject.ui.asset.fragments

import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateAssetBinding
import com.son.finalproject.ui.asset.viewmodels.AssetViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateAssetFragment : BaseFragment<FragmentCreateAssetBinding, AssetViewModel>() {
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_create_asset
}