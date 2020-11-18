package com.example.customnotification.LogAppListAdapter

import android.content.Context

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.BitmapConverter
import com.example.customnotification.LogDetailActivity
import kotlinx.android.synthetic.main.list.view.*
import java.text.SimpleDateFormat
import java.util.*


class Holder(v: View,c: Context?) : RecyclerView.ViewHolder(v){
    var view : View = v
    var context: Context? = c
    var cal : Calendar = Calendar.getInstance()
    var format :SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
    fun bind(item: AllNotificationList) {
        view.list_title.text = item.title
        view.list_text.text = item.text
        view.list_bigtext.text = item.bigtext
        Log.v("시간",beforeTime(format.parse(item.date)))
        view.list_date.text = beforeTime(format.parse(item.date))
        var icon = BitmapConverter().StringToBitmap(item.icon)
        view.list_icon.setImageBitmap(icon)
        view.list_appname.text = item.appname
        view.setOnClickListener {
            Log.v("포지션",position.toString())
            val nextIntent = Intent(context, LogDetailActivity::class.java)
            nextIntent.putExtra("appname",item.appname)
            nextIntent.putExtra("position",item.position!!.toInt())
            context!!.startActivity(nextIntent)
        }
        var picture = BitmapConverter().StringToBitmap(item.picture)
        view.list_picture.setImageBitmap(picture)
//        var picture1 = BitmapConverter().StringToBitmap(item.picture1)
//        view.list_picture.setImageBitmap(picture1)
    }

    fun beforeTime(date: Date): String? {
        val c = Calendar.getInstance()
        val now = c.timeInMillis
        val dateM = date.time
        var gap = now - dateM
        var ret = ""

//        초       분   시
//        1000    60  60
        gap = (gap / 1000)
        val hour = gap / 3600
        gap = gap % 3600
        val min = gap / 60
        val sec = gap % 60
        ret = if (hour > 24) {
            SimpleDateFormat("yyyy.MM.dd HH:mm").format(date)
        } else if (hour > 0) {
            hour.toString() + "시간 전"
        } else if (min > 0) {
            min.toString() + "분 전"
        } else if (sec > 0) {
            sec.toString() + "초 전"
        } else {
            SimpleDateFormat("yyyy.MM.dd HH:mm").format(date)
        }
        return ret
    }
}