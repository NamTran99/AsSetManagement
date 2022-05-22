package com.son.finalproject.utils;

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.son.finalproject.R




@BindingAdapter("android:text")
fun text(view : TextView,title : Int){
    if(title != 0){
        view.text = view.context.getString(title)
    }
}
