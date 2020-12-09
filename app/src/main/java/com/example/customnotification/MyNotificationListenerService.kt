package com.example.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Base64
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppDetail
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.EventBus.MessageEvent
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.text.SimpleDateFormat
import java.util.*



class MyNotificationListenerService : NotificationListenerService() {
    var mContext: Context = this
    var db : AppDB? = null
    var contactsList = mutableListOf<AppName>()
    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.e("kobbi", "MyNotificationListener.onListenerConnected()")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e("kobbi", "MyNotificationListener.onListenerDisconnected()")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.e("what",getActiveNotifications().toString())
        sbn?.packageName?.run {
            Log.e("kobbi", "MyNotificationListener.onNotificationPosted() --> packageName: $this")
            var notificatin: Notification = sbn.notification
            Log.v("메시지 시간",sbn.postTime.toString())
            var extras: Bundle = notificatin.extras
            var bigtext: CharSequence? = extras?.getCharSequence(Notification.EXTRA_BIG_TEXT)?.toString()?: ""
//            var title? = null
            var title = extras?.getString(Notification.EXTRA_TITLE)?: ""
            var text : String? = extras?.getCharSequence(Notification.EXTRA_TEXT)?.toString()?: ""
            var date = SimpleDateFormat(
                "yyyy.MM.dd HH:mm",
                Locale.KOREA
            ).format(sbn.postTime)
            var subText = extras?.getCharSequence(Notification.EXTRA_SUB_TEXT)
            //kakao profile picture
            var picture = extras?.get(Notification.EXTRA_LARGE_ICON) as? Bitmap

            var picture1 = extras?.get(Notification.EXTRA_PICTURE) as? Bitmap

            var appIcon: Drawable? = null
            var pm: PackageManager = applicationContext.packageManager
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

            var da: Drawable? = null
            try {
                remotePackageContext = applicationContext.createPackageContext(pack, 0)
//                icon = remotePackageContext.getResources().getDrawable(notificatin.smallIcon.resId)
                da = mContext.packageManager.getApplicationIcon(pack)

                if (da != null) {
                    bmp = getBitmapFromDrawable(da)


                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            val baos = ByteArrayOutputStream()
            bmp?.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b: ByteArray = baos.toByteArray()


            val baos1 = ByteArrayOutputStream()
//            picture?.compress(Bitmap.CompressFormat.PNG, 100, baos1)
            val b1: ByteArray = baos1.toByteArray()


            val msgrcv = Intent("Msg")
            if (extras != null) {
                val keys: Set<String> = extras.keySet()
                val it = keys.iterator()
                Log.e("LOG_TAG", "Dumping Intent start")
                while (it.hasNext()) {
                    val key = it.next()
                    Log.e("LOG_TAG", "[" + key + "=" + extras.get(key) + "]")
                }
                Log.e("LOG_TAG", "Dumping Intent end")
            }




//            val pref = this.getPreferences(0)
            if (text.equals(bigtext.toString())) {
                bigtext = ""
//                Log.v("빅텍스트가 참임", bigtext)
            }
            var arr = sbn.key.split("|")
            Log.v("배열", arr.toString())

            if (arr[1].equals("com.facebook.orca") && arr[2].equals("20001")) {

            } else if (arr[1].equals("com.kakao.talk") && extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
                    ?.toString().orEmpty().contains("안 읽은 메시지")
            ) {
                Log.v(
                    "안읽은메시지",
                    extras.getCharSequence(Notification.EXTRA_SUB_TEXT)?.contains("안 읽은 메시지")
                        .toString()
                )
            } else if (text.equals("null")||text==null||text.equals("")) {

            } else if (arr[1].equals("com.google.android.googlequicksearchbox")) {

            } else if (title==null) {

            }
            else {
                if (extras.get(Notification.EXTRA_LARGE_ICON).toString().contains("Icon")) {
                    Log.v("아이콘포함", "ㄹㅇ")
                    var drawable: Icon = extras.get(Notification.EXTRA_LARGE_ICON) as Icon


                    Log.v("아이콘포함", drawable.loadDrawable(applicationContext).toString())
                    val bitmapIcon = drawable.loadDrawable(applicationContext).toBitmap()
                    picture = bitmapIcon
                    Log.v("아이콘포함", bitmapIcon.toString())
                }
//                var crashtics = FirebaseCrashlytics.getInstance()
//                crashtics.setCustomKey("package", extras.toString())
//                crashtics.log(extras.toString())
                Log.v(
                    "안읽은메시지",
                    extras.getCharSequence(Notification.EXTRA_SUB_TEXT)?.toString().orEmpty()
                        .contains("안 읽은 메시지").toString()
                )
                db = AppDB.getInstance(mContext)
//            db!!.DAO().deleteAll_app_name()
                var name_class = AppName(null,appName,appName+".png")
                if(!db!!.DAO().isRowIsExist(appName)){
                    db!!.DAO().insertAll_app_name(name_class)
                    Log.v("이미지경로",bitmapToFile(bmp!!,appName).toString())

                }
                Log.v("null check",appName)
                Log.v("null check",title)
                Log.v("null check",text)
                Log.v("null check",bigtext.toString())
                Log.v("null check",date)
                Log.v("null check",bitmapToFile(picture))
                var detail_class = AppDetail(null,appName?: "",title?: "",text?: "",bigtext.toString()?: "",date,bitmapToFile(picture))
                db!!.DAO().insertAll_app_detail(detail_class)
                Log.v("데이터베이스",db!!.DAO().getAll_app_name().toString())
                Log.v("데이터베이스",db!!.DAO().getAll_app_detail()[0].name)
//                Log.v("데이터베이스 조인 최근",db!!.DAO().getAll_lately().get(0).toString())
                System.out.println(db!!.DAO().getAll_lately())
                Log.v("현재 타이틀", title)
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msgrcv);
                msgrcv.putExtra("title", title)
                msgrcv.putExtra("text", text)
                msgrcv.putExtra("date", date)
                msgrcv.putExtra("icon", bmp)
                msgrcv.putExtra("appname", appName)
                EventBus.getDefault().post(MessageEvent("Hello everyone!"));
            }


//            Log.v("노티",list.getAll().toString())

            var test = sbn.notification
            Log.i("NotificationListener", "[snowdeer] EXTRA_MESSAGES:$test")






            if (extras.containsKey(Notification.EXTRA_PICTURE)) {
                bmp = extras[Notification.EXTRA_PICTURE] as Bitmap?
                val byteArrayOutputStream =
                    ByteArrayOutputStream()
                bmp!!.compress(CompressFormat.PNG, 100, byteArrayOutputStream)
                var byteArrayS = byteArrayOutputStream.toByteArray()
                var encoded = Base64.encodeToString(byteArrayS, Base64.DEFAULT)
                val LOGCAT_MAX_LENGTH = 3950
                if (BuildConfig.DEBUG) {
                    while (encoded.length > LOGCAT_MAX_LENGTH) {
                        var substringIndex: Int = encoded.lastIndexOf(",", LOGCAT_MAX_LENGTH)
                        if (substringIndex == -1) substringIndex = LOGCAT_MAX_LENGTH
                        Log.d("encode", encoded.substring(0, substringIndex))
                        encoded = encoded.substring(substringIndex).trim()
                    }
                    Log.d("encode", encoded)
                }
            }
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
    private fun bitmapToFile(bitmap:Bitmap , imagename: String): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images",Context.MODE_PRIVATE)
        file = File(file,"${imagename}.png")

        try{
            // Compress the bitmap and save in jpg format
            val stream:OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }
    private fun bitmapToFile(bitmap:Bitmap?): String {
        // Get the context wrapper
        if(bitmap !=null){
            val wrapper = ContextWrapper(applicationContext)

            // Initialize a new file instance to save bitmap object
            var file = wrapper.getDir("Images",Context.MODE_PRIVATE)
            file = File(file,"${UUID.randomUUID()}.png")
            Log.v("널첵","널아님 이미지 파일화함")
            try{
                // Compress the bitmap and save in jpg format
                val stream:OutputStream = FileOutputStream(file)
                bitmap.setHasAlpha(true)
                bitmap?.compress(Bitmap.CompressFormat.PNG,100,stream)
                stream.flush()
                stream.close()
            }catch (e:IOException){
                e.printStackTrace()

            }
            Log.v("널첵","널아님 이미지 파일화함")
            // Return the saved bitmap uri
            return Uri.parse(file.name).toString()
        }
        else{
            Log.v("널첵","널임")
        }
        return "null"
    }

    private fun saveImage(finalBitmap: Bitmap, image_name: String) {
        val root: String = Environment.getExternalStorageDirectory().toString()
        val myDir = File(root)
        myDir.mkdirs()
        val fname = "Image-$image_name.jpg"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        Log.i("LOAD", root + fname)
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

}