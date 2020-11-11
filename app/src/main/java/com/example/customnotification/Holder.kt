package com.example.customnotification

import android.R.id.icon1
import android.graphics.Bitmap
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*


class Holder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(item: AllNotificationList) {
        view.list_title.text = item.title
        view.list_text.text = item.text
        view.list_date.text = item.date
        var icon = BitmapConverter().StringToBitmap(item.icon)
        view.list_icon.setImageBitmap(icon)
        view.list_appname.text = item.appname
        var picture = BitmapConverter().StringToBitmap(item.picture)
        view.list_picture.setImageBitmap(picture)
    }
}