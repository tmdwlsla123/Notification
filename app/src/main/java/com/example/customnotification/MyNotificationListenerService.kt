package com.example.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("OverrideAbstract")
class MyNotificationListenerService: NotificationListenerService() {
    var mContext: Context = this
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
            var text = extras.getCharSequence(Notification.EXTRA_TEXT).toString()
            var date = SimpleDateFormat("HH:mm", Locale.KOREA).format(Calendar.getInstance().time)
            var subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)



            val msgrcv = Intent("Msg")
            msgrcv.putExtra("title", title)
            msgrcv.putExtra("text", text)
            msgrcv.putExtra("date", date)

            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msgrcv);

//            val pref = this.getPreferences(0)
            val list = AppCache(mContext)
            list.saveNotification(title,text,date)


            Log.v("노티",list.getAll().toString())



            Log.i("NotificationListener", "[snowdeer] Title:$title")
            Log.i("NotificationListener", "[snowdeer] Text:$text")
            Log.i("NotificationListener", "[snowdeer] Sub Text:$subText")
        }
    }
}