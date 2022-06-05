package com.son.finalproject.ui.management.asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.User
import com.son.finalproject.databinding.ItemFieldUserManagementBinding
import com.son.finalproject.ui.management.user.viewmodels.UserManagementViewModel

class UserAdapter(private val viewModel: UserManagementViewModel): BaseRecyclerViewAdapter<User, ItemFieldUserManagementBinding>() {
    override val layoutId: Int
        get() = R.layout.item_field_user_management

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldUserManagementBinding>,
        position: Int
    ) {
        val item = items[position]
        holder.binding.apply {
            user = item
        }
    }
}