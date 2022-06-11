package com.son.finalproject.ui.management.asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Asset
import com.son.finalproject.data.User
import com.son.finalproject.databinding.ItemFieldUserManagementBinding
import com.son.finalproject.ui.management.user.viewmodels.UserManagementViewModel

class UserAdapter: BaseRecyclerViewAdapter<User, ItemFieldUserManagementBinding>() {
    override val layoutId: Int
        get() = R.layout.item_field_user_management

    // text: tên thuộc tính muốn xóa
    private var mOnClickRemove : ((text: User) -> Unit)? = null
    private var mOnClickEdit : ((text: User) -> Unit)? = null

    fun setOnClickRemove(func: ((text: User) ->Unit)?): UserAdapter{
        mOnClickRemove = func
        return this
    }

    fun setOnClickEdit(func: ((text: User) ->Unit)?): UserAdapter{
        mOnClickEdit = func
        return this
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldUserManagementBinding>,
        position: Int
    ) {
        val item = items[position]
        holder.binding.apply {
            user = item

            btnRemove.setOnClickListener {
                mOnClickRemove?.invoke(item)
            }
            btnEdit.setOnClickListener {
                mOnClickEdit?.invoke(item)
            }
        }
    }
}