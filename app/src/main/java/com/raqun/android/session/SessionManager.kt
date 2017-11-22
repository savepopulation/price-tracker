package com.raqun.android.session

import android.app.Activity
import android.content.Intent
import com.raqun.android.RaqunApp
import com.raqun.android.model.User
import com.raqun.android.ui.logout.LogoutActivity

/**
 * Created by tyln on 21/09/2017.
 */
class SessionManager constructor(private val session: String) : SessionProvider {

    private lateinit var currentState: State
    private var currentUser: User? = null

    @Synchronized
    override fun updateSession(user: User?) {
        currentUser = user
        currentState = initState()
    }

    @Synchronized
    override fun dropSession() {
        currentUser = null
        currentState = initState()
    }

    @Synchronized
    override fun getCurrentUser() = currentUser

    @Synchronized
    override fun getSession() = session

    @Synchronized
    override fun getState() = currentState

    @Synchronized
    private fun initState(): State {
        return if (currentUser != null) {
            State.LOGIN
        } else {
            State.LOGOUT
        }
    }

    @Synchronized
    override fun invalidateSession(activity: Activity) {
        if (currentState != State.LOGIN) {
            return
        }

        currentState = State.INVALID
        LogoutActivity.newIntent(activity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }.also {
            activity.startActivity(it)
        }
    }
}