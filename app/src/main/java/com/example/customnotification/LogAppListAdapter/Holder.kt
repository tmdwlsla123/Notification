package com.example.customnotification.LogAppListAdapter

import android.content.Context

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.BitmapConverter
import com.example.customnotification.LogDetailActivity
import kotlinx.android.synthetic.main.list.view.*


class Holder(v: View,c: Context?) : RecyclerView.ViewHolder(v){
    var view : View = v
    var context: Context? = c
    fun bind(item: AllNotificationList) {
        view.list_title.text = item.title
        view.list_text.text = item.text
        view.list_bigtext.text = item.bigtext
        view.list_date.text = item.date
        var icon = BitmapConverter().StringToBitmap(item.icon)
        view.list_icon.setImageBitmap(icon)
        view.list_appname.text = item.appname
        view.setOnClickListener {
            Log.v("포지션",position.toString())
            val nextIntent = Intent(context, LogDetailActivity::class.java)
            nextIntent.putExtra("appname",item.appname)
            nextIntent.putExtra("position",position)
            context!!.startActivity(nextIntent)
        }
        var picture = BitmapConverter().StringToBitmap(item.picture)
        view.list_picture.setImageBitmap(picture)
//        var picture1 = BitmapConverter().StringToBitmap(item.picture1)
//        view.list_picture.setImageBitmap(picture1)
    }
}