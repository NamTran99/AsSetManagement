package com.son.finalproject.utils.helper

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.son.finalproject.R
import java.nio.file.Files.size
import java.text.SimpleDateFormat
import java.util.*


fun Fragment.findNavController(): NavController = NavHostFragment.findNavController(this)

fun Fragment.hideKeyboard(view: View) {
    val inputMethodManager: InputMethodManager? =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showTimePickerDialog(
    formatType: String = "dd/MM/yyyy",
    onReceiveDateTime: (String) -> Unit
) {
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(requireContext(), { _, year, month, date ->
        val calendar = GregorianCalendar()
        calendar.set(Calendar.DAY_OF_MONTH, date)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)

        val viewFormatter = SimpleDateFormat(formatType, Locale.US)
        onReceiveDateTime(
            viewFormatter.format(calendar.time)
        )
    }, year, month, dayOfMonth).show()
}

fun View.showPopUp(menu : Int = R.menu.menu_request_state, action: (Int)->Unit){
    setOnClickListener {
        PopupMenu(context,this).apply {
            inflate(menu)
            setOnMenuItemClickListener {item->
                (it as? TextView)?.let {textView->
                    textView.text = item.title
                    action.invoke(item.itemId)
                }
                true
            }
            show()
        }
    }
}