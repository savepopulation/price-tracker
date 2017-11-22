package com.raqun.android.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 29/07/2017.
 */
data class DefaultResponse<T>(
        @SerializedName("response_code") val code: Int,
        @SerializedName("response_text") val message: String,
        @SerializedName("response_data") val data: T
)