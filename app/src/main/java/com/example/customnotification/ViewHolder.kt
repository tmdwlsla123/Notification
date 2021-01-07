package com.example.customnotification

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.UtilClass.UtilClass
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.notification.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.SimpleDateFormat


class ViewHolder(v: View, context: Context) : RecyclerView.ViewHolder(v) {
    var view: View = v
    var context: Context? = context
    var format: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")

    fun bind(item: List<AppNameAndAppDetail1>) {
            view.notification_title.text = item.get(position).appdetail!!.title
            view.notification_text.text = item.get(position).appdetail!!.text
            view.notification_appname.text = item.get(position).appname!!.app_name
            view.notification_date.text =
                UtilClass.beforeTime(format.parse(item.get(position).appdetail!!.date))
            view.notification_icon.setImageBitmap(loadImageFromStorage(item.get(position).appname!!.app_icon))
            view.notification_parent.setOnClickListener {

            }

    }

    private fun loadImageFromStorage(file: String?): Bitmap? {
        val wrapper = ContextWrapper(context)
        val path = wrapper.getDir("Images", Context.MODE_PRIVATE)
        var b: Bitmap? = null
        try {

            val f = File(path, file)
            b = BitmapFactory.decodeStream(FileInputStream(f))

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return b
    }
}
