package com.example.customnotification

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*

class Holder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v

    fun bind(item: AllNotificationList) {
        view.list_title.text = item.text
        view.list_text.text = item.title
        view.list_date.text = item.date
    }
}