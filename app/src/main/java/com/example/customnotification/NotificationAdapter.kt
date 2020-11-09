package com.example.customnotification

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NotificationAdapter (val context: Context, val notificationList:  ArrayList<NotificationList>) : BaseAdapter(){
    private val Context = context
    private val mItem = notificationList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder
        Log.v("뷰 생성","확인용")
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.notification, null)
            holder = ViewHolder()
            holder.title = view.findViewById(R.id.notification_title)
            holder.text = view.findViewById(R.id.notification_text)
            holder.date = view.findViewById(R.id.notification_date)
            holder.appname = view.findViewById(R.id.notification_appname)
            holder.icon = view.findViewById(R.id.notification_icon)
            Log.v("뷰 생성","확인용")

            view.tag = holder
            /* convertView가 null, 즉 최초로 화면을 실행할 때에
            ViewHolder에 각각의 TextView와 ImageView를 findVidwById로 설정.
            마지막에 태그를 holder로 설정한다. */

        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
            /* 이미 만들어진 View가 있으므로, tag를 통해 불러와서 대체한다. */
        }

        val list = notificationList[position]

//        val resourceId = context.resources.getIdentifier(dog.photo, "drawable", context.packageName)
        holder.title?.text = list.title
        holder.text?.text = list.text
        holder.date?.text = list.date
        holder.appname?.text = list.appname
        holder.icon?.setImageBitmap(list.icon)
//        holder.icon?.text = list.icon
        /* holder와 실제 데이터를 연결한다. null일 수 있으므로 변수에 '?'을 붙여 safe call 한다. */

        return view
    }

    override fun getItem(position: Int): Any {
        return notificationList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return notificationList.size
    }

    private class ViewHolder {
        var title : TextView? = null
        var text: TextView? = null
        var date: TextView? = null
        var appname: TextView? = null
        var icon: ImageView? = null
//        var icon: ImageView? = null

    }

    fun setItem(items: java.util.ArrayList<NotificationList>) {
        if (notificationList != null) {
            notificationList.clear()
            notificationList.addAll(items)
            notifyDataSetChanged()
        }
    }
}