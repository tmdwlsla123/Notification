package com.example.customnotification

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.example.customnotification.MainFragment.MainFragment
import com.example.customnotification.MainFragment.NotiFragment
import com.example.customnotification.receiver.BroadCastReceiver
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a.setOnClickListener{
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,
                    MainFragment()
                )
                .commit();
        }
        b.setOnClickListener{
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,
                    NotiFragment()
                )
                .commit();
        }
        c.setOnClickListener {
            val list = AppCache(this)
            list.clear()
            Log.v("클리어",list.getAll().toString())
        }



        button.setOnClickListener {
            if (!isNotificationPermissionAllowed())
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));

            /**
             * Notification 접근 권한 체크 메서드
             * @return 접근권한이 있을 경우 true, 아니면 false
             */

        }
        onBtn.setOnClickListener {
                val intent = Intent(applicationContext, ScreenService::class.java)
                startService(intent)
        }
        offBtn.setOnClickListener{
                val intent = Intent(applicationContext, ScreenService::class.java)
                stopService(intent)
        }
        now_start.setOnClickListener {
            val intent = Intent(this, LockScreenActivity::class.java)
            startActivity(intent)
        }

        send.setOnClickListener {
            val br : BroadcastReceiver = BroadCastReceiver()
            val filter = IntentFilter().apply{
                addAction(Intent.ACTION_SCREEN_ON)

            }
            registerReceiver(br, filter)
//            Toast.makeText(this,"?",Toast.LENGTH_SHORT).show()
        }

    }



    private fun isNotificationPermissionAllowed(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(applicationContext)
                .any { enabledPackageName ->
                    enabledPackageName == packageName
                }
    }
}
