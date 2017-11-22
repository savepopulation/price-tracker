package com.raqun.android.api.request

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 25/10/2017.
 */
data class RegisterTokenRequest(@SerializedName("device_key") val token: String)