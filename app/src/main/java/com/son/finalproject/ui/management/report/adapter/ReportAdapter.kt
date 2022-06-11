package com.son.finalproject.ui.management.report.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Report
import com.son.finalproject.databinding.ItemFieldReportBinding

class ReportAdapter: BaseRecyclerViewAdapter<Report, ItemFieldReportBinding>() {
    override val layoutId = R.layout.item_field_report

    override fun onBindViewHolder(holder: BaseViewHolder<ItemFieldReportBinding>, position: Int) {
        val item = items[position]

        holder.binding.apply {
            report = item
        }
    }
}