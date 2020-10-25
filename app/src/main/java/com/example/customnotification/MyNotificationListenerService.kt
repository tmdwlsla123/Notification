package com.example.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


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
            var notificatin: Notification = sbn.notification
            var extras: Bundle = notificatin.extras
            var title = extras.getString(Notification.EXTRA_TITLE)
            var text = extras.getCharSequence(Notification.EXTRA_TEXT)
            var subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
            val msgrcv = Intent("Msg")
            msgrcv.putExtra("title", title)
            msgrcv.putExtra("text", text)

            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msgrcv);



            Log.i("NotificationListener", "[snowdeer] Title:$title")
            Log.i("NotificationListener", "[snowdeer] Text:$text")
            Log.i("NotificationListener", "[snowdeer] Sub Text:$subText")
        }
    }
}