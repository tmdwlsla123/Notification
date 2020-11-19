package com.example.customnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customnotification.LogDetailAdapter.AllNotificationList1
import com.example.customnotification.LogDetailAdapter.ListAdapter1
import kotlinx.android.synthetic.main.fragment_noti.*
import kotlinx.android.synthetic.main.recyleview.*

class LogDetailActivity : AppCompatActivity() {
    val COUNT_KEY = "count_key"
    var arrayList = ArrayList<AllNotificationList1>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_detail)
        var appname = intent.getStringExtra("appname")
        Log.v("인텐트 앱네임",appname)

        var AppCache = AppCache(this)
        var app_count = AppCache.getInt(COUNT_KEY,0)
        var position = intent.getIntExtra("position",0)
        Log.v("인텐트 포지션",position.toString())

        for (i in 1..AppCache.getInt(appname, 0)) {
            val AllNotificationList = AllNotificationList1()
            AllNotificationList.title = AppCache.getString("${appname}_title$i", "")
            Log.v("현재시간",AppCache.getString("${appname}_title$i", ""))

            AllNotificationList.text = AppCache.getString("${appname}_text$i", "")

            AllNotificationList.bigtext = AppCache.getString("${appname}_bigtext$i", "")
            if(AppCache.getString("${appname}_bigtext$i", "0").equals("null")){
                AllNotificationList.bigtext = ""
            }

            AllNotificationList.date = AppCache.getString("${appname}_date$i", "")

            AllNotificationList.icon = AppCache.getString("icon$position", "")

            AllNotificationList.appname = AppCache.getString("appname$position", "")

            AllNotificationList.picture = AppCache.getString("${appname}_picture$i", "")

//            AllNotificationList.picture1 = AppCache.getString("picture$i", "0")

//            Log.v("arraylist", AppCache.getString("title$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("text$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("date$i", "0"))

//            Log.v("arraylist", AppCache.getString("icon$i", "0"))
            arrayList.add(0, AllNotificationList)


        }

        val ListAdapter = ListAdapter1(arrayList)
        noti_list.adapter = ListAdapter
        val lm = LinearLayoutManager(this)
        noti_list.layoutManager = lm
        noti_list.setHasFixedSize(true)
        ListAdapter.notifyDataSetChanged()
    }
}