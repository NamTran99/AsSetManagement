package com.son.finalproject.ui.home.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentReportBinding
import com.son.finalproject.ui.home.viewmodels.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportFragment : BaseFragment<FragmentReportBinding, ReportViewModel>() {
    override val viewModel: ReportViewModel by viewModels()
    override val layoutId = R.layout.fragment_report
}