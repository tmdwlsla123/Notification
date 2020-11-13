package com.example.customnotification.LogDetailAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.R

class ListAdapter1 (private val item:  ArrayList<AllNotificationList1>) : RecyclerView.Adapter<Holder1>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder1 {

      val  view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder1(view)
    }

    override fun getItemCount(): Int {

        return item.size
    }

    override fun onBindViewHolder(holder1: Holder1, position: Int) {


        val item = item[position]

        holder1.apply {
            bind(item)
        }
    }
}
