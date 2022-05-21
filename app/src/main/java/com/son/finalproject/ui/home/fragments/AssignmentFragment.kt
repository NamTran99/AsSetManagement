package com.son.finalproject.ui.home.fragments

import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssignmentBinding
import com.son.finalproject.ui.asset.viewmodels.AssetViewModel

class AssignmentFragment : BaseFragment<FragmentAssignmentBinding, AssetViewModel>() {
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_assignment
}