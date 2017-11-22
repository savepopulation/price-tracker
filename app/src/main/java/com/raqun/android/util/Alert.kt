package com.raqun.android.util

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import com.raqun.android.Constants
import com.raqun.android.R

/**
 * Created by tyln on 24/08/2017.
 */
object Alert {

    fun create(context: Context,
               title: CharSequence?,
               message: CharSequence,
               cancelable: Boolean,
               @StringRes positiveButton: Int,
               @StringRes negaviteButton: Int,
               positive: () -> Unit,
               negative: () -> Unit): AlertDialog {

        return with(AlertDialog.Builder(context)) {
            title?.let { setTitle(title) }
            setMessage(message)
            if (positiveButton != Constants.NO_RES) {
                setPositiveButton(context.getString(positiveButton), { dialogInterface, i ->
                    dialogInterface.dismiss()
                    positive()
                })
            }
            if (negaviteButton != Constants.NO_RES) {
                setNegativeButton(context.getString(negaviteButton), { dialogInterface, i ->
                    dialogInterface.dismiss()
                    negative()
                })
            }
            setCancelable(cancelable)
            create()
        }
    }

}
