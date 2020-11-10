package com.example.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
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
            var date = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA).format(Calendar.getInstance().time)
            var subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
            var iconid = notificatin.smallIcon.loadDrawable(mContext)

            var smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON)
            var appIcon: Drawable? = null
            var pm :PackageManager = applicationContext.packageManager
//            var ai = pm.getApplicationLabel(packageManager.getApplicationInfo(this,pack))
            val packageManager = applicationContext.packageManager
            val appName = packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(
                    this,
                    PackageManager.GET_META_DATA
                )
            ) as String
            try {
                appIcon = notificatin.smallIcon.loadDrawable(mContext)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val bitmap = getBitmapFromDrawable(appIcon!!)

//            Log.v("아이콘",smallIconRes.toString())
//            Log.v("아이콘",appIcon.toString())
//            Log.v("아이콘",bitmap.toString())
//            Log.v("아이콘",iconid.toString())
//            Log.v("아이콘",notificatin.toString())
            var pack = this

            var remotePackageContext: Context? = null
            var bmp: Bitmap? = null

            var icon: Drawable? = null

            var da : Drawable? = null
            try {
                remotePackageContext = applicationContext.createPackageContext(pack, 0)
                icon  = remotePackageContext.getResources().getDrawable(notificatin.smallIcon.resId)
                 da  = mContext.packageManager.getApplicationIcon(pack)
                if (da != null) {
                    bmp = getBitmapFromDrawable(da)


                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            val baos = ByteArrayOutputStream()
            bmp?.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b: ByteArray = baos.toByteArray()





            val msgrcv = Intent("Msg")
            msgrcv.putExtra("title", title)
            msgrcv.putExtra("text", text)
            msgrcv.putExtra("date", date)
            msgrcv.putExtra("icon", b)
            msgrcv.putExtra("appname", appName)

            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msgrcv);

//            val pref = this.getPreferences(0)
            val list = AppCache(mContext)
            if(-1 ==sbn.key.indexOf("0|com.facebook.orca|20001|null|") ){

                list.saveNotification(title,text,date,bmp,appName)
            }



//            Log.v("노티",list.getAll().toString())




            Log.i("NotificationListener", "[snowdeer] Title:$title")
            Log.i("NotificationListener", "[snowdeer] Text:$text")
            Log.i("NotificationListener", "[snowdeer] Sub Text:$subText")
            Log.i("NotificationListener", "[snowdeer] Appname:${appName.toString()}")
            Log.i("NotificationListener", "[snowdeer] icon:${icon.toString()}")
            Log.i("NotificationListener", "[snowdeer] bmp:${bmp.toString()}")
            Log.i("NotificationListener", "[snowdeer] test:${da}")
            Log.i("NotificationListener", "[snowdeer] key:${sbn.key.indexOf("0|com.facebook.orca|20001|null|")}")



        }
    }
    private fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
        val bmp = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bmp)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bmp
    }
    private fun saveBitmapAsFile(bitmap: Bitmap, filepath: String) {
        val file = File(filepath)
        var os: OutputStream? = null
        try {
            file.createNewFile()
            os = FileOutputStream(file)
            bitmap.compress(CompressFormat.PNG, 100, os)
            os.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

}