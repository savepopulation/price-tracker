package com.raqun.android.api.request

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 13/11/2017.
 */
data class AlarmRequest(@SerializedName("user_product_id") private val productId: String)