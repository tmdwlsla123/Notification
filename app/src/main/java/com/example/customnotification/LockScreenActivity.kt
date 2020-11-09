package com.example.customnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_lock_screen.*
import kotlinx.android.synthetic.main.notification.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer


class LockScreenActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var arrayList = ArrayList<NotificationList>()
    val notificationadapter: NotificationAdapter? = null
    private val ACTION_NOTIFICATION_LISTENER_SETTINGS =
        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")
//        val a = intent.getStringExtra("")
//        val header: View = layoutInflater.inflate(R.layout.lock_screen_header, null, false)
//        notifi_list.addHeaderView(header)
//        val footer : View = layoutInflater.inflate(R.layout.lock_screen_footer, null, false)
//        notifi_list.addFooterView(footer)



        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, IntentFilter("Msg"))
        //잠금화면 시간 UI
        var timer = timer(period = 1000) {
            // 1초마다 실행할 블록
            val currentDateTime = Calendar.getInstance().time
            var dateFormat1 =
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
            var dateFormat = SimpleDateFormat("HH:mm", Locale.KOREA).format(currentDateTime)
            var dateFormat2 = SimpleDateFormat("MM월 dd일 E요일", Locale.KOREA).format(currentDateTime)
            // 백그라운드로 실행되는 부분, UI조작 X
            runOnUiThread {
                date_time.setText(dateFormat)
                date_day.setText(dateFormat2)
//                Log.v("TAG", dateFormat1)
            }
        }
        lock_open.setOnClickListener {
            finish()
            timer?.cancel()
        }
        //알림 레이아웃 부모

        val myNotificationListenerService = MyNotificationListenerService()
//        Log.v("브로드캐스트 유무",)


        val textView = TextView(this)
        textView.text = "글자"



    }

    private var onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            var title = intent.getStringExtra("title")
            var text = intent.getStringExtra("text")
            var date = intent.getStringExtra("date")
            var appname = intent.getStringExtra("appname")

            val arr = intent.getByteArrayExtra("icon")
            var image = BitmapFactory.decodeByteArray(arr, 0, arr.size)



            val NotificationList = NotificationList()



            NotificationList.title = title
            NotificationList.text = text
            NotificationList.date = date
            NotificationList.icon = image
            NotificationList.appname = appname




            arrayList.add(0,NotificationList)
            val notificationadapter = NotificationAdapter(applicationContext, arrayList)
            notifi_list.adapter = notificationadapter


            Log.v("리스트 배열",notificationadapter.toString())
            Log.v("리스트 배열",arrayList.toString())
            //

            Log.v("브로드캐스트", "$<b>$title : </b>$text")
        }
    }




    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (hasFocus) window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    )
        }
    }

    @Suppress("DEPRECATION")
    override fun onAttachedToWindow() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        )

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        super.onAttachedToWindow()
    }


}

