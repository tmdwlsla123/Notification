package com.example.customnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if (!isNotificationPermissionAllowed())
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));

            /**
             * Notification 접근 권한 체크 메서드
             * @return 접근권한이 있을 경우 true, 아니면 false
             */

        }

    }

    private fun isNotificationPermissionAllowed(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(applicationContext)
                .any { enabledPackageName ->
                    enabledPackageName == packageName
                }
    }
}
