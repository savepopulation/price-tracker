package com.raqun.android.session

import android.app.Activity
import com.raqun.android.model.User
import com.raqun.android.ui.BaseActivity

/**
 * Created by tyln on 24/10/2017.
 */
interface SessionProvider {

    fun getCurrentUser(): User?

    fun getSession(): String

    fun dropSession()

    fun getState(): State

    fun updateSession(user: User?)

    fun invalidateSession(activity: Activity)
}

