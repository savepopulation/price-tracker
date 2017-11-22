package com.raqun.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 27/09/2017.
 */
data class Notification(val id: String,
                        val content: String,
                        val key: String,
                        @SerializedName("notification_type") val type: Int,
                        @SerializedName("notification_status") val status: Int,
                        @SerializedName("created_at") val createDate: String,
                        val product: Product?)