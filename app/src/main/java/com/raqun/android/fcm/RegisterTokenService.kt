package com.raqun.android.fcm

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.google.firebase.iid.FirebaseInstanceId
import com.raqun.android.RaqunApp
import com.raqun.android.api.RaqunServices
import com.raqun.android.api.request.RegisterTokenRequest
import com.raqun.android.util.SharedPrefUtil
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 26/10/2017.
 */
class RegisterTokenService : IntentService("RegisterTokenService") {

    @Inject lateinit var raqunServices: RaqunServices

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onHandleIntent(p0: Intent?) {
        val forceRefresh = p0?.extras?.getBoolean(BUNDLE_REFRESH, false)

        var token: String? = null

        if (forceRefresh != true) {
            token = SharedPrefUtil.get(this,
                    RaqunApp.sessionManager.getSession(),
                    null)
        }

        if (token == null) {
            token = FirebaseInstanceId.getInstance().token
            if (token != null) {
                raqunServices.registerToken(RegisterTokenRequest(token))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onComplete = {
                                    SharedPrefUtil.put(this,
                                            RaqunApp.sessionManager.getSession(),
                                            token)
                                },
                                onError = {
                                    // omit error
                                })
            }
        }
    }

    companion object {
        private const val BUNDLE_REFRESH = "force_refresh"

        fun newIntent(context: Context, refresh: Boolean = false) = Intent(context,
                RegisterTokenService::class.java).apply {
            putExtra(BUNDLE_REFRESH, refresh)
        }
    }
}