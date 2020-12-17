package com.example.customnotification

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification.view.*


class ViewHolder(v: View, context: Context) : RecyclerView.ViewHolder(v) {
    var view: View = v
    var context: Context? = context
    fun bind(item: NotificationList) {
        view.notification_title.text = item.title
        view.notification_text.text = item.text
        view.notification_appname.text = item.appname
        view.notification_date.text = item.date
        view.notification_icon.setImageBitmap(item.icon)

    }
}