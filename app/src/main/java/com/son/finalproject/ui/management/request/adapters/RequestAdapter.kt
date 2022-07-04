package com.son.finalproject.ui.management.request.adapters

import com.son.finalproject.R
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.data.Request
import com.son.finalproject.databinding.ItemFieldRequestBinding

class RequestAdapter(private val isAdmin: Boolean): BaseRecyclerViewAdapter<Request, ItemFieldRequestBinding>() {
    override val layoutId= R.layout.item_field_request

    var mAcceptOnclick: ((Request) ->Unit)? = null
    var mDeclineOnclick: ((Request) ->Unit)? = null
    var mRemoveOnclick: ((Request) ->Unit)? = null

    fun setAcceptClickButton(func: ((Request) ->Unit)): RequestAdapter{
        mAcceptOnclick = func
        return this
    }

    fun setDeclineClickButton(func: ((Request) ->Unit)): RequestAdapter{
        mDeclineOnclick = func
        return this
    }

    fun setRemoveClickButton(func: ((Request) ->Unit)): RequestAdapter{
        mRemoveOnclick = func
        return this
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemFieldRequestBinding>, position: Int) {

        val item = items[position]
        holder.binding.apply {
            index = (position + 1).toString()
            request = item
            isAdmin = this@RequestAdapter.isAdmin
            state.text = when(item.status){
                0 -> "Requesting"
                1 -> "Declined"
                2 -> "Completed"
                else -> "Requesting"
            }

            btnAccept.setOnClickListener {
                mAcceptOnclick?.invoke(item)
            }
            btnDeclined.setOnClickListener {
                mDeclineOnclick?.invoke(item)
            }
            btnRemove.setOnClickListener {
                mRemoveOnclick?.invoke(item)
            }
        }
    }
}