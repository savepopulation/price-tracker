package com.raqun.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 13/11/2017.
 */
data class Alarm(private val id: String,
                 @SerializedName("user_product_id") val userProductId: String,
                 @SerializedName("setting_id") val alarmId: Int,
                 @SerializedName("setting_text") val alarmText: String,
                 @SerializedName("exact_value") val exactVal: Double,
                 @SerializedName("is_active") val isActive: Boolean)