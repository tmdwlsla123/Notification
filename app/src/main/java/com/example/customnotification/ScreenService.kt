package com.example.customnotification

import android.app.Service
import android.app.Service.START_REDELIVER_INTENT
import android.content.Intent

import android.content.IntentFilter

import android.os.IBinder
import android.util.Log


class ScreenService : Service() {
    private var mReceiver: ScreenReceiver? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mReceiver = ScreenReceiver()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(mReceiver, filter)
        Log.e("잠금화면","잠금화면 생성")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {
            if (intent.action == null) {
                if (mReceiver == null) {
                    mReceiver = ScreenReceiver()
                    val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
                    registerReceiver(mReceiver, filter)
                }
            }
        }
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
        }
    }
}