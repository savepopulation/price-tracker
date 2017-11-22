package com.raqun.android.util

import android.content.Context.MODE_PRIVATE
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences


/**
 * Created by tyln on 09/08/2017.
 */
object SharedPrefUtil {

    // TODO create an extension for this

    private val MASTER_KEY = "!r4-qun?"

    private fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(MASTER_KEY, MODE_PRIVATE)
    }

    fun put(context: Context, key: String, value: String?) {
        getSharedPref(context).edit().putString(key, value).apply()
    }

    /*fun put(context: Context, key: String, value: Int) {
        context.getSharedPreferences(MASTER_KEY, Context.MODE_PRIVATE).edit().putInt(key, value).apply()
    }*/

    fun clearData(context: Context) {
        getSharedPref(context).edit().clear().apply()
    }

    fun get(context: Context, key: String, defaultVal: String?): String? {
        return getSharedPref(context).getString(key, defaultVal)
    }

    fun remove(context: Context, key: String) {
        getSharedPref(context).edit().remove(key).apply()
    }
}
