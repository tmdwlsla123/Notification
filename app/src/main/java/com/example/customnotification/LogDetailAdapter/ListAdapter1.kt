package com.example.customnotification.LogDetailAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.R

class ListAdapter1 (private val item:  ArrayList<AppNameAndAppDetail1>,context: Context) : RecyclerView.Adapter<Holder1>(){
    private var contacts: List<AppNameAndAppDetail1> = listOf()
    private var context: Context?=context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder1 {

      val  view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder1(view,context)
    }

    override fun getItemCount(): Int {

        return contacts.size
    }

    override fun onBindViewHolder(holder1: Holder1, position: Int) {


        val item = contacts[position]

        holder1.apply {
            bind(item)
        }
    }
    fun setContacts(contacts: List<AppNameAndAppDetail1>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}
