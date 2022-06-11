package com.son.finalproject.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

// Base adapter: hỗ trợ tạo UI cho các Data giống nhau
abstract class BaseRecyclerViewAdapter<T : Any, VB : ViewBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<VB>>() {

    protected var items = mutableListOf<T>()
    var listener: ((view : View, item : T, position : Int)->Unit )? = null

    // Cơ chế update imtem mà có sự khác nhau
    fun updateItems(items: MutableList<T>) {
        Log.d("TAG", "updateItems: ${this.items}")
        val taskDiffCallBack = BaseDiffCallBack<T>(this.items,items)
        val diffResult = DiffUtil.calculateDiff(taskDiffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(items)
        Log.d("TAG", "updateItems: ${this.items}")
    }

    // Bản "khuôn" UI dùng để đổ data
    @get:LayoutRes
    protected abstract val layoutId: Int

    // số lượng các data
    override fun getItemCount() = items.size

    // tạo khuôn "UI"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    )

    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}