package com.son.finalproject.utils;

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.son.finalproject.R

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/22/2022
*/
@BindingAdapter("android:text")
fun text(view : TextView,title : Int){
    if(title != 0){
        view.text = view.context.getString(title)
    }
}
