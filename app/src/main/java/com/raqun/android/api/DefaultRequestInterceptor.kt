package com.raqun.android.api

import com.raqun.android.BuildConfig
import com.raqun.android.RaqunApp
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by tyln on 22/07/2017.
 */
class DefaultRequestInterceptor @Inject constructor()
    : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        chain?.let {
            return chain.proceed(with(chain.request().newBuilder()) {
                addHeader("Content-Type", "application/json")
                addHeader("ChannelType", BuildConfig.CHANNEL_KEY.toString())
                addHeader("VersionCode", BuildConfig.VERSION_CODE.toString())
                addHeader("VersionName", BuildConfig.VERSION_NAME)
                addHeader("ApiKey", BuildConfig.API_KEY)
                addHeader("ApplicationId", BuildConfig.APPLICATION_ID)
                addHeader("SessionKey", RaqunApp.sessionManager.getSession())

                val user = RaqunApp.sessionManager.getCurrentUser()
                user?.let {
                    addHeader("Authorization", it.getAuthorization())
                }
                build()
            })
        }

        return null
    }
}