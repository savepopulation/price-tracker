package com.raqun.android.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 13/08/2017.
 */
class PagedResponse<T>(
        @SerializedName("items") val items: List<T>,
        @SerializedName("is_first_page") val isFirstPage: Boolean,
        @SerializedName("is_last_page") val isLastPage: Boolean,
        @SerializedName("start") val start: Int
)