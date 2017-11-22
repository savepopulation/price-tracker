package com.raqun.android.extensions

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.raqun.android.R
import com.raqun.android.api.ApiConstants
import com.raqun.android.data.DataBean
import com.raqun.android.ui.BaseView

/**
 * Created by tyln on 12/10/2017.
 */
fun AppCompatActivity.init(savedInstanceState: Bundle?, fragment: Fragment) {
    if (savedInstanceState == null) {
        this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.framelayout_main, fragment)
                .commit()
    }
}

inline fun FragmentManager.transact(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun FragmentManager.openFragment(@IdRes flId: Int, fragment: Fragment) {
    beginTransaction()
            .replace(flId, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
}

fun Context.getErrorMessage(errorCode: Int): String {
    return try {
        val resId = resources.getIdentifier("api_error_message" + errorCode,
                "string", packageName)
        getString(resId)
    } catch (e: Resources.NotFoundException) {
        getString(R.string.error_message_unknown)
    }
}

fun Context.alert(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.forceCloseKeyboard() {
    if (currentFocus != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

inline fun <T> LiveData<DataBean<T>>.observeApi(lifecycleOwner: LifecycleOwner,
                                                    crossinline body: (DataBean<T>?) -> Unit) {
    observe(lifecycleOwner, Observer { bean: DataBean<T>? ->
        if (bean != null && bean.hasError()) {
            if (lifecycleOwner is BaseView) {
                lifecycleOwner.onError(bean.getError())
            }
        }
        body(bean)
    })
}