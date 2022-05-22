package com.son.finalproject.ui.asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.databinding.ItemFieldManagementBinding

class AssetAdapter: BaseRecyclerViewAdapter<Pair<Asset,Category>, ItemFieldManagementBinding>() {
    override val layoutId: Int
        get() = R.layout.item_field_management

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldManagementBinding>,
        position: Int
    ) {
        val item = items[position]
        holder.binding.apply {
            asset = item.first
            category = item.second
            state.text = if(item.first.status == 0) "Available" else "Not Available"
        }
    }
}