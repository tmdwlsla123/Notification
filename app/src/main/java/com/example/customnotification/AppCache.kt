package com.example.customnotification

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Picture
import android.util.Log


class AppCache(context: Context?) {
    private val PREF = "notificationApp"
    private val COUNT_KEY = "count_key"
    private val sharedPref: SharedPreferences =
        context!!.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveNotification(
        title: String?,
        text: String?,
        bigtext: CharSequence?,
        date: String?,
        icon: Bitmap?,
        appname: String,
        picture: Bitmap?,
        picture1: Bitmap?
    ) {

        var count = sharedPref.getInt(COUNT_KEY, 0)
        var log_count = sharedPref.getInt(appname, 0)
//        var i = 0
        for (i in 0..count) {
            Log.v("반복문", "실행횟수" + i)
            Log.v("반복문", "count" + count)
            Log.v("반복문", "$appname=" + sharedPref.getString("appname$i", ""))
            if (sharedPref.getString("appname${i + 1}", "").equals(appname)) {
                Log.v("참", "기존에 앱 이름 과 일치 후 반복문 조료")
                sharedPref.edit().putString("title${i + 1}", title).apply()
                sharedPref.edit().putString("text${i + 1}", text).apply()
                sharedPref.edit().putString("date${i + 1}", date).apply()
//                sharedPref.edit().putString("date${count}", date).apply()
//                sharedPref.edit().putString("appname${count}", appname).apply()
//                val image = BitmapConverter().BitmapToString(icon)
//                sharedPref.edit().putString("icon$count", image).apply()
                break;
            } else if (sharedPref.getString("appname${i + 1}", "").equals("")) {
                Log.v("거짓", "기존에 앱 이름 과 일치 후 반복문 조료")
                sharedPref.edit().putString("title${i + 1}", title).apply()
                sharedPref.edit().putString("text${i + 1}", text).apply()
                sharedPref.edit().putString("bigtext${i + 1}", bigtext.toString()).apply()
                sharedPref.edit().putString("date${i + 1}", date).apply()
                sharedPref.edit().putString("appname${i + 1}", appname).apply()
                val image = BitmapConverter().BitmapToString(icon)
                sharedPref.edit().putString("icon${i + 1}", image).apply()
//                val picture_s = BitmapConverter().BitmapToString(picture)
//                sharedPref.edit().putString("picture$count", picture_s).apply()
                count++
                break;
            }

        }

        sharedPref.edit().putInt(COUNT_KEY, count).apply()

        sharedPref.edit().putString("${appname}_title${log_count + 1}", title).apply()
        sharedPref.edit().putString("${appname}_text${log_count + 1}", text).apply()
        sharedPref.edit().putString("${appname}_date${log_count + 1}", date).apply()
        sharedPref.edit().putString("${appname}_bigtext${log_count + 1}", bigtext.toString()).apply()

//                val image = BitmapConverter().BitmapToString(icon)
//                sharedPref.edit().putString("${appname}_icon$log_count", image).apply()
                val picture_s = BitmapConverter().BitmapToString(picture)
//                val picture_ss = BitmapConverter().BitmapToString(picture1)
                sharedPref.edit().putString("${appname}_picture${log_count + 1}", picture_s).apply()
        log_count++
        sharedPref.edit().putInt(appname, log_count).apply()
//                sharedPref.edit().putString("${appname}_picture1$log_count", picture_ss).apply()


//        for ((key, value) in map) {
//            Log.v("흠", "[key]:$key, [value]:$value")
//            if(value.equals(appname))
//        }
//        sharedPref.edit().putString("title$count", title).apply()
//        sharedPref.edit().putString("text$count", text).apply()


//        sharedPref.edit().putString("date$count", date).apply()
//        sharedPref.edit().putString("appname$count", appname).apply()
//        val image = BitmapConverter().BitmapToString(icon)
//        sharedPref.edit().putString("icon$count", image ).apply()
//
//
//        val picture_s = BitmapConverter().BitmapToString(picture)
//        val picture_ss = BitmapConverter().BitmapToString(picture1)
//
//        sharedPref.edit().putString("picture$count",picture_s).apply()
//        sharedPref.edit().putString("picture1$count",picture_ss).apply()
        //saving count in prefs
//        sharedPref.edit().putInt(COUNT_KEY, count).apply()
    }

    fun getString(key: String, defValue: String): String {
        return sharedPref.getString(key, defValue).toString()
    }

//    fun setString(key: String, str: String) {
//        sharedPref.edit().putString(key, str).apply()
//    }

    fun getInt(key: String, int: Int): Int {
        return sharedPref.getInt(key, int)
    }

    fun getAll(): Map<String, *> {
        return sharedPref.all
    }

    fun clear() {
        sharedPref.edit().clear().apply()
    }

}