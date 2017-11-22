package com.raqun.android

import android.app.Activity
import android.app.Application
import android.app.Service
import com.crashlytics.android.Crashlytics
import com.raqun.android.data.source.local.UserHelper
import com.raqun.android.di.DaggerAppComponent
import com.raqun.android.session.SessionManager
import com.raqun.android.session.SessionProvider
import com.raqun.android.util.SharedPrefHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.fabric.sdk.android.Fabric
import java.util.*
import javax.inject.Inject

/**
 * Created by tyln on 22/07/2017.
 */
class RaqunApp : Application(), HasActivityInjector, HasServiceInjector {

    @Inject lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        RaqunApp.sessionManager = SessionManager(getOrCreateSessionKey()).also {
            it.updateSession(UserHelper.getUserCredentials(this))
        }

        DaggerAppComponent.builder().let {
            it.application(this)
            it.build()
        }.also {
            it.inject(this)
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun getOrCreateSessionKey(): String {
        var session: String? = SharedPrefHelper.getSession(this, null)
        if (session == null) {
            session = UUID.randomUUID().toString()
            SharedPrefHelper.putSession(this, session)
        }
        return session
    }

    companion object {
        lateinit var sessionManager: SessionProvider
            private set
    }
}