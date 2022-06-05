package com.son.finalproject.ui.home.fragments

import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssignmentBinding
import com.son.finalproject.ui.management.asset.viewmodels.CreateAssetViewModel

class AssignmentFragment : BaseFragment<FragmentAssignmentBinding, CreateAssetViewModel>() {
    override val viewModel: CreateAssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_assignment
}