package com.example.customnotification

import android.app.ActivityManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.Process.myPid
import android.service.notification.NotificationListenerService
import android.util.Log


class NotificationCollectorMonitorService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() called")
        ensureCollectorRunning()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun ensureCollectorRunning() {
        val collectorComponent = ComponentName(
            this,  /*NotificationListenerService Inheritance*/
            NotificationListenerService::class.java
        )
        Log.v(
            TAG,
            "ensureCollectorRunning collectorComponent: $collectorComponent"
        )
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var collectorRunning = false
        val runningServices =
            manager.getRunningServices(Int.MAX_VALUE)
        if (runningServices == null) {
            Log.w(
                TAG,
                "ensureCollectorRunning() runningServices is NULL"
            )
            return
        }
        for (service in runningServices) {
            if (service.service == collectorComponent) {
                Log.w(
                    TAG,
                    "ensureCollectorRunning service - pid: " + service.pid + ", currentPID: " + myPid() + ", clientPackage: " + service.clientPackage + ", clientCount: " + service.clientCount
                            + ", clientLabel: " + if (service.clientLabel == 0) "0" else "(" + resources.getString(
                        service.clientLabel
                    ) + ")"
                )
                if (service.pid == myPid() /*&& service.clientCount > 0 && !TextUtils.isEmpty(service.clientPackage)*/) {
                    collectorRunning = true
                }
            }
        }
        if (collectorRunning) {
            Log.d(
                TAG,
                "ensureCollectorRunning: collector is running"
            )
            return
        }
        Log.d(
            TAG,
            "ensureCollectorRunning: collector not running, reviving..."
        )
        toggleNotificationListenerService()
    }

    private fun toggleNotificationListenerService() {
        Log.d(
            TAG,
            "toggleNotificationListenerService() called"
        )
        val thisComponent = ComponentName(
            this,  /*getClass()*/
            NotificationListenerService::class.java
        )
        val pm = packageManager
        pm.setComponentEnabledSetting(
            thisComponent,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
        pm.setComponentEnabledSetting(
            thisComponent,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        /**
         * [Log.isLoggable]
         *
         *
         * IllegalArgumentException is thrown if the tag.length() > 23.
         */
        private const val TAG = "NotifiCollectorMonitor"
    }
}