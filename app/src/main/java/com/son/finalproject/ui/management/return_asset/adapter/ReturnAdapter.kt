package com.son.finalproject.ui.management.return_asset.adapter

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.Assignment.Companion.STATE_COMPLETED
import com.son.finalproject.data.Assignment.Companion.STATE_RETURNED
import com.son.finalproject.data.Assignment.Companion.STATE_RETURNING
import com.son.finalproject.databinding.ItemFieldAssignManagementBinding
import com.son.finalproject.databinding.ItemFieldReturnManagementBinding

class ReturnAdapter(private val isAdminType: Boolean) : BaseRecyclerViewAdapter<Assignment , ItemFieldReturnManagementBinding>(){
    override val layoutId = R.layout.item_field_return_management


    private var mOnclickReturn : ((Assignment) -> Unit)? = null
    private var mOnCLickAccept : ((Assignment) -> Unit)? = null
    private var mOnclickDecline : ((Assignment) -> Unit)? = null

    fun setOnCLickReturn(func: ((Assignment) ->Unit)?): ReturnAdapter {
        mOnclickReturn = func
        return this
    }

    fun setOnClickAccept(func: ((Assignment) ->Unit)?): ReturnAdapter {
        mOnCLickAccept = func
        return this
    }

    fun setOnClickDecline(func: ((Assignment) ->Unit)?): ReturnAdapter {
        mOnclickDecline = func
        return this
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemFieldReturnManagementBinding>,
        position: Int
    ) {
        val item = items[position]

        holder.binding.apply {
            isAdmin = isAdminType
            assignment = item
            index = (position + 1).toString()

            state.text = when(item.status){
                STATE_COMPLETED -> "Holding"
                STATE_RETURNING -> "Returning"
                STATE_RETURNED -> "Returned"
                else -> "Returning"
            }

            btnReturn.text = if(item.status == 1) "RETURN" else "CANCEL"

            btnReturn.setOnClickListener {
                mOnclickReturn?.invoke(item)
            }

            btnAccept.setOnClickListener {
                mOnCLickAccept?.invoke(item)
            }
            btnDeclined.setOnClickListener {
                mOnclickDecline?.invoke(item)
            }
        }
    }
}