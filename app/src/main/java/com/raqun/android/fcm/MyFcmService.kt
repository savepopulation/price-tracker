package com.raqun.android.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.raqun.android.R
import com.raqun.android.model.NotificationType
import com.raqun.android.ui.NavigationController
import com.raqun.android.ui.main.MainActivity
import com.raqun.android.ui.product.ProductActivity

/**
 * Created by tyln on 24/10/2017.
 */
class MyFcmService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        remoteMessage?.let {
            if (getString(R.string.gcm_defaultSenderId) == remoteMessage.from) {
                remoteMessage.notification?.let {
                    var intent = MainActivity.newIntent(this)
                    if (remoteMessage.data?.size!! > 0) {
                        val payload = remoteMessage.data
                        val intentFactory: NavigationController.IntentFactory
                                = NavigationController.DefaultIntentFactory(this)
                        intent = intentFactory.createNotificationIntent(payload["notification_type"]!!.toInt(),
                                payload["key"])
                    }

                    val title = if (it.title == null) {
                        getString(R.string.app_name)
                    } else {
                        it.title
                    }

                    val message = if (it.body == null) {
                        getString(R.string.notification_default_message)
                    } else {
                        it.body
                    }

                    showNotification(title, message, intent)
                }
            }
        }
    }

    private fun showNotification(title: String?, message: String?, intent: Intent) {
        val pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = "raqun"
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, with(NotificationCompat.Builder(this, channelId)) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(message)
            setAutoCancel(true)
            setSound(soundUri)
            setContentIntent(pendingIntent)
            build()
        })
    }
}