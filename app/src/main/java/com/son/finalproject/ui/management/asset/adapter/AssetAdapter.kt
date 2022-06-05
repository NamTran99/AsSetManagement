package com.son.finalproject.ui.management.asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.databinding.ItemFieldAssetManagementBinding
import com.son.finalproject.ui.management.asset.viewmodels.AssetManagementViewModel

class AssetAdapter(private val viewModel: AssetManagementViewModel): BaseRecyclerViewAdapter<Pair<Asset,Category>, ItemFieldAssetManagementBinding>() {
    override val layoutId: Int
        get() = R.layout.item_field_asset_management

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldAssetManagementBinding>,
        position: Int
    ) {
        val item = items[position]
        holder.binding.apply {
            asset = item.first
            category = item.second
            state.text = if(item.first.status == 0) "Available" else "Not Available"
            btnRemove.setOnClickListener {
                viewModel.removeAssetCode(item.first.assetCode)
            }
        }
    }
}