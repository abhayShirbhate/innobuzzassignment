package com.abhay.innobuzzassignment.utils

import android.app.AlertDialog
import android.content.Context

object Utils {
    const val ACCESSIBILITY_PERMISSION_ACCEPTED_TEXT = "Accessibility permission accepted"
    const val REQUEST_ACCESSIBILITY_PERMISSION_TEXT = "Give accessibility permission"

    fun showMessageDialog(context: Context, title: String, message: String,listener: ()-> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            listener()
        }
        val dialog = builder.create()
        dialog.show()
    }

}