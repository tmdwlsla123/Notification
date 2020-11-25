package com.example.customnotification.LogAppListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.R

class ListAdapter (private val item:  List<AppName>, val context : Context?,val db: AppDB) : RecyclerView.Adapter<Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

      val  view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder(
            view,
            context
        )
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
}
