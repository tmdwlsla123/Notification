package com.example.customnotification.SearchAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.LogAppListAdapter.Holder
import com.example.customnotification.R

class SearchAdapter (private val item:  List<AppNameAndAppDetail1>, val context : Context?) : RecyclerView.Adapter<Holder2>(){
    private var contacts: List<AppNameAndAppDetail1> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder2 {

        val  view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return Holder2(
            view,
            context
        )
    }

    override fun getItemCount(): Int {

        return contacts.size
    }

    override fun onBindViewHolder(holder2: Holder2, position: Int) {


        val item: AppNameAndAppDetail1 = contacts[position]

        holder2.apply {
            bind(item)
        }
    }
    fun setContacts(contacts: List<AppNameAndAppDetail1>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}