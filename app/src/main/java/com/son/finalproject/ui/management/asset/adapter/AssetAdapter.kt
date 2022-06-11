package com.son.finalproject.ui.management.asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.databinding.ItemFieldAssetManagementBinding
import com.son.finalproject.ui.management.asset.viewmodels.AssetManagementViewModel
import com.son.finalproject.utils.dialog.DeleteDialog

class AssetAdapter(): BaseRecyclerViewAdapter<Pair<Asset,Category>, ItemFieldAssetManagementBinding>() {
    override val layoutId: Int
        get() = R.layout.item_field_asset_management

    // text: tên thuộc tính muốn xóa
    private var mOnClickRemove : ((text: Asset) -> Unit)? = null
    private var mOnClickEdit : ((text: Asset) -> Unit)? = null

    fun setOnClickRemove(func: ((text: Asset) ->Unit)?): AssetAdapter{
        mOnClickRemove = func
        return this
    }

    fun setOnClickEdit(func: ((text: Asset) ->Unit)?): AssetAdapter{
        mOnClickEdit = func
        return this
    }

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
                mOnClickRemove?.invoke(item.first)
            }

            btnEdit.setOnClickListener {
                mOnClickEdit?.invoke(item.first)
            }
        }

    }
}