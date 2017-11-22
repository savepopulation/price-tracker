package com.raqun.android.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.raqun.android.R
import com.raqun.android.extensions.loadImage
import com.squareup.picasso.Picasso

/**
 * Created by tyln on 02/08/2017.
 */
object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        imageView.loadImage(url)
    }
}