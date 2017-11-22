package com.raqun.android.extensions

import android.accounts.NetworkErrorException
import com.raqun.android.api.ApiConstants
import com.raqun.android.data.Error
import retrofit2.HttpException
import java.text.DecimalFormat

/**
 * Created by tyln on 16/10/2017.
 */
fun Throwable?.getError(): Error {
    if (this == null) {
        return Error(0, "")
    }

    return when (this) {
        is HttpException -> Error(this.code(), this.message())
        is NetworkErrorException -> Error(ApiConstants.ERROR_CODE_NETWORK, "")
        else -> Error(0, "")
    }
}

fun Double?.formatToPrice(): String? {
    val priceFormat = DecimalFormat("0.#")
    return priceFormat.format(this)
}