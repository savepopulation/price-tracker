package com.raqun.android.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by tyln on 29/07/2017.
 */

@Parcelize
@SuppressLint("ParcelCreator")
data class Product(
        @SerializedName("id") val productId: String,
        val name: String,
        val url: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("web_site_id") val webSiteId: String,
        @SerializedName("web_site_name") val webSiteName: String,
        val price: Double,
        val currency: String,
        val quantity: Int,
        @SerializedName("last_modified_at") val updateDate: String?,
        @SerializedName("follower_total") val followersCount: Int
) : Parcelable