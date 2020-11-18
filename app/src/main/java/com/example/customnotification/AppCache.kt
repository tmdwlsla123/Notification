package com.example.customnotification

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class AppCache(context: Context?) {
    private val PREF = "notificationApp"
    private val sort = "sort"
    private val COUNT_KEY = "count_key"
    private val SORT_APP_DATA = "count_sort_key"
    private val sharedPref: SharedPreferences =
        context!!.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    private val sharedPref_sort: SharedPreferences =
        context!!.getSharedPreferences(sort, Context.MODE_PRIVATE)

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
        val picture_s = BitmapConverter().BitmapToString(picture)
        var sort_app_data = sharedPref.getInt(SORT_APP_DATA, 0)
//        var i = 0
        for (i in 0..count) {
            Log.v("반복문", "실행횟수" + i)
            Log.v("반복문", "count" + count)
            Log.v("반복문", "$appname=" + sharedPref.getString("appname${i + 1}", ""))
            if (sharedPref.getString("appname${i + 1}", "").equals(appname)) {
                Log.v("참", "기존에 앱 이름 과 일치 후 반복문 조료")
                sharedPref.edit().putString("title${i + 1}", title).apply()
                sharedPref.edit().putString("text${i + 1}", text).apply()
                sharedPref.edit().putString("bigtext${i + 1}", bigtext.toString()).apply()
                sharedPref.edit().putString("date${i + 1}", date).apply()
                sharedPref.edit().putString("picture${i + 1}", picture_s).apply()
//                sharedPref.edit().putString("date${count}", date).apply()
//                sharedPref.edit().putString("appname${count}", appname).apply()
//                val image = BitmapConverter().BitmapToString(icon)
//                sharedPref.edit().putString("icon$count", image).apply()
                break;
            } else if (sharedPref.getString("appname${i + 1}", "").equals("")) {
                Log.v("거짓", "기존에 앱 이름 과 일치 하지않음 반복문 조료")
                sharedPref.edit().putString("title${i + 1}", title).apply()
                sharedPref.edit().putString("text${i + 1}", text).apply()
                sharedPref.edit().putString("bigtext${i + 1}", bigtext.toString()).apply()
                sharedPref.edit().putString("date${i + 1}", date).apply()
                sharedPref.edit().putString("appname${i + 1}", appname).apply()
                val image = BitmapConverter().BitmapToString(icon)
                sharedPref.edit().putString("icon${i + 1}", image).apply()
                sharedPref.edit().putString("picture${i + 1}", picture_s).apply()
//                val picture_s = BitmapConverter().BitmapToString(picture)
//                sharedPref.edit().putString("picture$count", picture_s).apply()
                count++
                break;
            }

        }
        //앱별 데이터를 정렬하기 위한 플래그 설정
        sort_app_data++
        //앱별로 데이터의 입력 순서를 카운트로 보유
        sharedPref_sort.edit().putInt(appname, sort_app_data).apply()
        //데이터를 받을때마다 1씩 증가하는 데이터 추가
        sharedPref.edit().putInt(SORT_APP_DATA, sort_app_data).apply()

        sharedPref.edit().putInt(COUNT_KEY, count).apply()

        sharedPref.edit().putString("${appname}_title${log_count + 1}", title).apply()
        sharedPref.edit().putString("${appname}_text${log_count + 1}", text).apply()
        sharedPref.edit().putString("${appname}_date${log_count + 1}", date).apply()
        sharedPref.edit().putString("${appname}_bigtext${log_count + 1}", bigtext.toString())
            .apply()

//                val image = BitmapConverter().BitmapToString(icon)
//                sharedPref.edit().putString("${appname}_icon$log_count", image).apply()
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
        sharedPref_sort.edit().clear().apply()
    }

    fun getSort(): ArrayList<Int> {
        var count = sharedPref.getInt(COUNT_KEY, 0)

        var arraylist: HashMap<String, Int> = HashMap()
        for (i in 0..count - 1) {
//            Log.v("시간값 앱별로 출력",sharedPref.getString("date${i+1}",""))
            var date1: Date = SimpleDateFormat(
                "yyyy.MM.dd HH:mm",
                Locale.KOREA
            ).parse(sharedPref.getString("date${i + 1}", ""))
            arraylist.put(sharedPref.getString("date${i + 1}", "")!!, i)
        }
//
//        val sortedMap = arraylist.toSortedMap()
//        Log.v("소트맵",sortedMap.toString())
        val tm: TreeMap<String, Int> = TreeMap<String, Int>(arraylist)


        val iteratorKey: Iterator<String> =
            tm.keys.iterator() //키값 오름차순 정렬(기본)


        var keylist: ArrayList<Int> = ArrayList()
        while (iteratorKey.hasNext()) {
            val key = iteratorKey.next()
            Log.v("출력", key.toString() + "," + tm.get(key))

            keylist.add(tm.get(key)!!.plus(1))
        }
        return keylist

    }

    fun dummy() : ArrayList<String>{
        var all: Map<String, *> = sharedPref_sort.all
        var sort_data: HashMap<Int, String> = HashMap()
        for ((key, value) in all.entries) {
            Log.d("map values", key + ": " + value.toString())
            sort_data.put(value as Int, key)
        }
        Log.v("해시", sort_data.toString())
        //역으로 정렬
        var r = sort_data.toSortedMap(compareByDescending { it })
        sort_data.clear()
        var arr : ArrayList<String> = ArrayList()
        for ((value, key) in r.entries) {
//            Log.d("map values", key + ": " + value.toString())
//           Log.v("키 찾기",findkey(key))
            arr.add(findkey1(key))
            //첫번째 인자: value = 패키지 순서 번호 , 두번째 인자: key = 패키지 이름
//            sort_data.put(findkey(key).toInt(),key)
        }
        Log.v("해시 번호", sort_data.toString())
        Collections.reverse(arr)
        return arr

    }

    fun findkey(value1: String): String {
        for ((key, value) in sharedPref.all.entries) {
//            Log.v("findkey함수실행","누적")
            if (value!!.equals(value1)) {
                Log.v("findkey함수실행","key : "+ key)
                return key.replace("[^0-9]".toRegex(), "")
            }
        }
        return "0"
    }
    fun findkey1(value1: String):String{
        var count = sharedPref.getInt(COUNT_KEY, 0)
        var hash : HashMap<String?,Int> = HashMap()
        for (i in 1..count){
            hash.put(sharedPref.getString("appname"+i,""),i)
        }
        return hash.get(value1).toString()
    }
}
