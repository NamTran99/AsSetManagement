package com.son.finalproject.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


fun Fragment.findNavController(): NavController = NavHostFragment.findNavController(this)
fun Fragment.hideKeyboard(view: View) {
    val inputMethodManager: InputMethodManager? =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}