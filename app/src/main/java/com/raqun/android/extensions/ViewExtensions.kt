package com.raqun.android.extensions

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.support.annotation.DrawableRes
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.raqun.android.R
import com.raqun.android.binding.ImageBindingAdapter
import com.raqun.android.data.DataState
import com.raqun.android.util.DividerDecorator
import com.squareup.picasso.Picasso
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by tyln on 12/10/2017.
 */
fun RecyclerView.setup(context: Context, orientation: Int = LinearLayoutManager.VERTICAL) {
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    this.setHasFixedSize(true)
}

fun RecyclerView.decorate() {
    this.addItemDecoration(DividerDecorator(this.context))
}

@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftingMode() {
    val menuView = this.getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        // ignored
    } catch (e: IllegalAccessException) {
        // ignored
    }
}

fun ProgressDialog.init(state: DataState) {
    when (state) {
        DataState.FETCHING -> {
            if (!this.isShowing) this.show()
        }

        DataState.ERROR -> {
            if (this.isShowing) this.dismiss()
        }

        DataState.SUCCESS -> {
            if (this.isShowing) this.dismiss()
        }
    }
}

fun ImageView.loadImage(url: String?) {
    if (url == null || url.isEmpty()) {
        showEmptyImage()
    } else {
        Picasso.with(context.applicationContext)
                .load(url)
                .placeholder(R.drawable.ic_photo_camera)
                .error(R.drawable.ic_photo_camera)
                .into(this)
    }
}

fun ImageView.showEmptyImage() {
    setImageResource(R.drawable.ic_photo_camera)
}

fun EditText.pasteFromClipBoard() {
    var text = ""
    val currentSdk = android.os.Build.VERSION.SDK_INT
    if (currentSdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
        val manager: android.text.ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE)
                as android.text.ClipboardManager
        try {
            text = manager.text.toString()
        } catch (e: Exception) {
            // ignored
        }
    } else {
        val manager: android.content.ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE)
                as android.content.ClipboardManager
        manager.primaryClip?.let {
            val item = manager.primaryClip.getItemAt(0)
            text = item.text.toString()
        }
    }

    if (!TextUtils.isEmpty(text)) setText(text)
}

fun EditText.clear() {
    setText("")
}

fun EditText.getUrl(): String? {
    return try {
        URL(text.toString())
        text.toString()
    } catch (e: MalformedURLException) {
        context.alert(context.getString(R.string.error_message_product_url))
        null
    }
}

fun CoordinatorLayout.snackThat(meesage: CharSequence,
                                buttonText: CharSequence,
                                singleShot: View.OnClickListener?) {
    val sb = Snackbar.make(this, meesage, Snackbar.LENGTH_INDEFINITE)
    if (singleShot != null) {
        sb.setAction(buttonText, singleShot)
    } else {
        sb.setAction(buttonText, { sb.dismiss() })
    }
    sb.show()
}