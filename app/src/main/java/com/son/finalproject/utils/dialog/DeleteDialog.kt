package com.son.finalproject.utils.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.son.finalproject.R
import com.son.finalproject.databinding.FragmentDialogDeleteBinding

// Tạo dialog khi người dùng nhấn delete
class DeleteDialog : DialogFragment() {

    private lateinit var binding: FragmentDialogDeleteBinding

    private var onClickYes: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_dialog_delete,
            null,
            false
        )

        binding.apply {
            btnDelete.setOnClickListener {
                onClickYes?.invoke()
                dismiss()
            }

            btnCancel.setOnClickListener {
                dismiss()
            }
            txtAreYouSure.text = content
        }



        return AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)
        }.setCancelable(false).create()
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val wlp = window?.attributes
        wlp?.let {
            it.gravity = Gravity.CENTER
            it.width = WindowManager.LayoutParams.MATCH_PARENT
            it.height = WindowManager.LayoutParams.WRAP_CONTENT
            it.windowAnimations = R.style.dialogAnimation
        }
        val inset = InsetDrawable(ColorDrawable(Color.TRANSPARENT), 90)
        window?.attributes = wlp
        window?.setBackgroundDrawable(inset)
    }

    fun setOnClickYes(func: (() -> Unit)?): DeleteDialog {
        onClickYes = func
        return this
    }

    private var content: String = "Are you sure?"

    @SuppressLint("SetTextI18n")
    fun setTitle(text: String): DeleteDialog {
        content = "Are you sure delete ${text.uppercase()}?"
        return this
    }

    companion object {
        const val TAG = "DeleteDialog"
    }
}