package com.raqun.android.fcm

import com.google.firebase.iid.FirebaseInstanceIdService
import com.raqun.android.api.RaqunServices
import javax.inject.Inject

/**
 * Created by tyln on 24/10/2017.
 */
class MyFcmInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        startService(RegisterTokenService.newIntent(this, refresh = true))
    }
}