package com.example.customnotification.LogDetailAdapter

import android.content.Context

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.BitmapConverter
import com.example.customnotification.LogDetailActivity
import kotlinx.android.synthetic.main.list.view.*


class Holder1(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(item: AllNotificationList1) {
        view.list_title.text = item.title
        view.list_text.text = item.text
        view.list_bigtext.text = item.bigtext
        view.list_date.text = item.date
        var icon = BitmapConverter().StringToBitmap(item.icon)
        view.list_icon.setImageBitmap(icon)
        view.list_appname.text = item.appname

        var picture = BitmapConverter().StringToBitmap(item.picture)
        view.list_picture.setImageBitmap(picture)
//        var picture1 = BitmapConverter().StringToBitmap(item.picture1)
//        view.list_picture.setImageBitmap(picture1)
    }
}