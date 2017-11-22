package com.raqun.android.util

import android.content.Context

/**
 * Created by tyln on 14/08/2017.
 */
class SharedPrefHelper private constructor() {

    companion object {
        val SESSION_KEY = "session"

        fun getSession(context: Context, defaultValue: String?)
                = SharedPrefUtil.get(context, SESSION_KEY, defaultValue)

        fun putSession(context: Context, session: String?)
                = SharedPrefUtil.put(context, SESSION_KEY, session)

    }
}