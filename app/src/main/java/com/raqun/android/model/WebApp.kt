package com.raqun.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 13/08/2017.
 */
data class WebApp(@SerializedName("web_site_id") val id: String,
                  @SerializedName("name") val name: String,
                  @SerializedName("domain") val url: String,
                  @SerializedName("image_url") val imageUrl: String,
                  @SerializedName("tracked_product_total") val totalTrackCount: Int)