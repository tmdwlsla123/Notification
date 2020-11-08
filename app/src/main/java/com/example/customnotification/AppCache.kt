package com.example.customnotification

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon


class AppCache(context: Context) {
    private val PREF = "notificationApp"
    private val COUNT_KEY = "count_key"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    fun saveNotification(title: String?, text: String?, date :String?, icon : Bitmap) {

        var count = sharedPref.getInt(COUNT_KEY, 0)
        count++
        sharedPref.edit().putString("title$count", title).apply()
        sharedPref.edit().putString("text$count", text).apply()
        sharedPref.edit().putString("date$count", date).apply()


        val image = BitmapConverter().BitmapToString(icon)
        sharedPref.edit().putString("icon$count", image ).apply()
        //saving count in prefs
        sharedPref.edit().putInt(COUNT_KEY, count).apply()
    }
    fun getString(key: String, defValue: String): String {
        return sharedPref.getString(key, defValue).toString()
    }

//    fun setString(key: String, str: String) {
//        sharedPref.edit().putString(key, str).apply()
//    }

    fun getInt(key: String, int: Int): Int{
        return sharedPref.getInt(key, int)
    }
    fun getAll():Map<String,*>{
        return sharedPref.all
    }
    fun clear(){
        sharedPref.edit().clear().apply()
    }

}