package com.son.finalproject.ui.management.assignment.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.User
import com.son.finalproject.databinding.ItemFieldAssignManagementBinding
import com.son.finalproject.ui.management.asset.adapter.UserAdapter

class AssignmentAdapter : BaseRecyclerViewAdapter<Assignment , ItemFieldAssignManagementBinding>(){
    override val layoutId = R.layout.item_field_assign_management


    // text: tên thuộc tính muốn xóa
    private var mOnClickRemove : ((text: Assignment) -> Unit)? = null

    fun setOnClickRemove(func: ((text: Assignment) ->Unit)?): AssignmentAdapter {
        mOnClickRemove = func
        return this
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldAssignManagementBinding>,
        position: Int
    ) {
        val item = items[position]

        holder.binding.apply {
            assignment = item
            index = (position + 1).toString()

            state.text = when(item.status){
                0 -> "declined"
                1 -> "completed"
                2 -> "waiting"
                else -> "declined"
            }

            btnRemove.setOnClickListener {
                mOnClickRemove?.invoke(item)
            }
        }
    }
}