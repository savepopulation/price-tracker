package com.raqun.android.ui

import com.raqun.android.data.Error

/**
 * Created by tyln on 27/07/2017.
 */
interface BaseView {
    fun onError(e: Error?)
}