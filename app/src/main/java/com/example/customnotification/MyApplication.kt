package com.example.customnotification

import android.app.Application
import android.util.Log

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler())
    }
//    inner class ExceptionHandler : Thread.UncaughtExceptionHandler {
//        override fun uncaughtException(t: Thread?, e: Throwable?) {
//            // 여기에 원하는 동작 구현
//            Log.v("비정상적인 종료", "비정상적인 종료")
//            e?.printStackTrace()
//            Log.v("비정상적인 종료", "비정상적인 종료")
//            android.os.Process.killProcess(android.os.Process.myPid())
//            System.exit(10)
//        }
//    }
}