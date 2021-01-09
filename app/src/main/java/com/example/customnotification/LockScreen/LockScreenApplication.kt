package com.example.customnotification.LockScreen

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

/**
 * 잠금화면 Example Application
 */
class LockScreenApplication : MultiDexApplication() {

    init {
        instance = this@LockScreenApplication
    }

    companion object {
        private var instance: LockScreenApplication? = null
        const val notificationId: Int = 1

        fun applicationContext() : Context? {
            return instance?.applicationContext
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this@LockScreenApplication)
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}