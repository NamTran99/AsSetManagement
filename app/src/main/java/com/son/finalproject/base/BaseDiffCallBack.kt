package com.son.finalproject.base

import androidx.recyclerview.widget.DiffUtil

// base phân biệt sự khác nhau trong listview, nếu khác nhau giữa item nào chỉ update UI cho item đó,
// => tăng performance
class BaseDiffCallBack<T>(
    private val oldTask: List<T>,
    private val newTask: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldTask.size
    override fun getNewListSize(): Int = newTask.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTask[oldItemPosition].hashCode() == newTask[newItemPosition].hashCode()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTask[oldItemPosition] == newTask[newItemPosition]
    }
}