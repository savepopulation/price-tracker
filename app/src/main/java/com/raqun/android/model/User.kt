package com.raqun.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 07/08/2017.
 */
data class User(@SerializedName("access_token") val token: String,
                @SerializedName("token_type") val tokenType: String,
                @SerializedName("user_name") val userName: String,
                @SerializedName("id") val id: String,
                @SerializedName("name_surname") val fullName: String,
                @SerializedName("email") val email: String) {

    fun getAuthorization() = tokenType + " " + token
}