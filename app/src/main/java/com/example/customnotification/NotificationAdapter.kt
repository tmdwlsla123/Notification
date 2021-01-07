package com.example.customnotification

import android.content.Context
import android.system.Os.bind
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.LogAppListAdapter.Holder
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder


import java.util.*
import kotlin.collections.ArrayList

class NotificationAdapter (val context: Context) : RecyclerView.Adapter<ViewHolder>(){
    var data: List<AppNameAndAppDetail1> = listOf()






    fun setContacts(data: List<AppNameAndAppDetail1>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val  view = LayoutInflater.from(parent.context).inflate(R.layout.notification, null)
        return ViewHolder(
            view,
            context
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: List<AppNameAndAppDetail1> = data

        holder.apply {
            bind(item)
        }
    }
}
