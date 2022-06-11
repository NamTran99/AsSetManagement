package com.son.finalproject.ui.management.report.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentReportBinding
import com.son.finalproject.ui.management.report.adapter.ReportAdapter
import com.son.finalproject.ui.management.report.viewmodels.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportFragment : BaseFragment<FragmentReportBinding, ReportViewModel>() {
    override val viewModel: ReportViewModel by viewModels()
    override val layoutId = R.layout.fragment_report
    private lateinit var reportAdapter: ReportAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reportAdapter = ReportAdapter()

        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerReport.adapter = reportAdapter
        }
        initObserve()
    }

    private fun initObserve() {
        viewModel.liveReport.observe(viewLifecycleOwner){
            reportAdapter.updateItems(it.toMutableList())
        }
    }

}