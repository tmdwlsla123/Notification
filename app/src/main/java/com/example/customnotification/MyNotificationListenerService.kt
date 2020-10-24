package com.example.customnotification

import android.annotation.SuppressLint
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationManagerCompat

@SuppressLint("OverrideAbstract")
class MyNotificationListenerService: NotificationListenerService() {
    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.e("kobbi","MyNotificationListener.onListenerConnected()")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e("kobbi","MyNotificationListener.onListenerDisconnected()")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.packageName?.run {
            Log.e("kobbi","MyNotificationListener.onNotificationPosted() --> packageName: $this")
        }
    }
}