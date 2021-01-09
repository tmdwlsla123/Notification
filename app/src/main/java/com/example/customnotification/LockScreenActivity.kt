package com.example.customnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.LockScreen.ButtonUnLock
import com.example.customnotification.LockScreen.ViewUnLock
import com.example.customnotification.ViewModel.ContactViewModel
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.synthetic.main.activity_lock_screen.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer


class LockScreenActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context?) : Intent {
            return Intent(context, LockScreenActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                }
        }
    }
    private var timer: Timer? = null
    val TAG = "MainActivity"

    val continents = ArrayList<AppNameAndAppDetail1>()

    val notificationadapter: NotificationAdapter? = null
    private val ACTION_NOTIFICATION_LISTENER_SETTINGS =
        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
    var creator: SwipeMenuCreator? = null
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        val notificationadapter = NotificationAdapter(applicationContext)
        notifi_list.adapter = notificationadapter
        val lm = LinearLayoutManager(applicationContext)
        notifi_list.layoutManager = lm
//        notifi_list.setHasFixedSize(true)
        //
        val swipeHelperCallback = SwipeHelperCallback(notificationadapter,application)
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(notifi_list)

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, IntentFilter("Msg"))
        //잠금화면 시간 UI
        timer = timer(period = 1000) {
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
                Log.v("TAG", dateFormat1)
            }
        }
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.lockscreen().observe(this, androidx.lifecycle.Observer {
                contacts -> notificationadapter!!.setContacts(contacts!!)
        })


    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onNotice)
    }

    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

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
    override fun onResume() {
        super.onResume()

        setButtonUnlock()
        setViewUnlock()
    }

    private fun setButtonUnlock() {
        swipeUnLockButton.setOnUnlockListenerRight(object : ButtonUnLock.OnUnlockListener {
            override fun onUnlock() {
                timer!!.cancel()
                finish()
            }
        })
    }


    private fun setViewUnlock() {
        lockScreenView.x = 0f
        lockScreenView.setOnTouchListener(object : ViewUnLock(this, lockScreenView) {
            override fun onFinish() {
                timer!!.cancel()
                finish()
                super.onFinish()

            }
        })
    }

    override fun onBackPressed() {

    }

}


