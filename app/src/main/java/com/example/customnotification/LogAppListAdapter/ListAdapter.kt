package com.example.customnotification.LogAppListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.R

class ListAdapter (private val item:  List<AppNameAndAppDetail>, val context : Context?) : RecyclerView.Adapter<Holder>(){
    private var contacts: List<AppNameAndAppDetail> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

      val  view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder(
            view,
            context
        )
    }

    override fun getItemCount(): Int {

        return contacts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        val item: AppNameAndAppDetail = contacts[position]

        holder.apply {
            bind(item)
        }
    }
    fun setContacts(contacts: List<AppNameAndAppDetail>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}
