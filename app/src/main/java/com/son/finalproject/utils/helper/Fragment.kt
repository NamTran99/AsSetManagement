package com.son.finalproject.utils.helper

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


fun Fragment.findNavController(): NavController = NavHostFragment.findNavController(this)

fun Fragment.hideKeyboard(view: View) {
    val inputMethodManager: InputMethodManager? =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.showPopUp(menu : Int){
    setOnClickListener {
        PopupMenu(context,this).apply {
            inflate(menu)
            setOnMenuItemClickListener {item->
                (it as? TextView)?.let {textView->
                    textView.text = item.title
                }
                true
            }
            show()
        }
    }
}