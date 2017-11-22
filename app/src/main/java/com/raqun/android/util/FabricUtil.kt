package com.raqun.android.util

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.raqun.android.data.DataState

/**
 * Created by tyln on 01/11/2017.
 */

object FabricUtil {
    const val EVENT_ADD_PRODUCT = "add_product"
    const val KEY_RESULT = "result"
    const val KEY_URL = "url"
}


fun Answers.reportAddProduct(dataState: DataState, url: String) {
    logCustom(CustomEvent(FabricUtil.EVENT_ADD_PRODUCT).apply {
        putCustomAttribute(FabricUtil.KEY_RESULT, dataState.name)
        putCustomAttribute(FabricUtil.KEY_URL, url)
    })
}

