package com.raqun.android.api.request

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 07/08/2017.
 */
data class RegisterRequest(@SerializedName("email") private val email: String,
                           @SerializedName("name_surname") private val fullName: String,
                           @SerializedName("contact_permission") private val contactPermission: Boolean,
                           @SerializedName("user_name") private val userName: String,
                           @SerializedName("password") private val password: String)