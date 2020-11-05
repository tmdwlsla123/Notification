package com.example.customnotification

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter ( private val item:  ArrayList<AllNotificationList>) : RecyclerView.Adapter<Holder>(){
//    private val Context = context
//    private val mItem = Allnotilist

//    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title = itemView.findViewById<TextView>(R.id.list_title)
//        val text = itemView.findViewById<TextView>(R.id.list_text)
//        val date = itemView.findViewById<TextView>(R.id.list_date)
//
//
//        fun bind (allNotificationList:  AllNotificationList) {
//
//            /* 나머지 TextView와 String 데이터를 연결한다. */
//            title?.text = allNotificationList.title
//            text?.text = allNotificationList.text
//            date?.text = allNotificationList.date
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        val item = item[position]

        holder.apply {
            bind(item)
        }

    }
//    class mViewH(view: View) : RecyclerView.ViewHolder(view!!) {
//        var title = view.findViewById<TextView>(R.id.list_title)
//        var text = view.findViewById<TextView>(R.id.list_text)
//        var date = view.findViewById<TextView>(R.id.list_date)
//
//
//    }


}