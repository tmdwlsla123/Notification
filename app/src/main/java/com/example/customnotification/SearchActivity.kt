package com.example.customnotification

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customnotification.LogDetailAdapter.AllNotificationList1
import com.example.customnotification.LogDetailAdapter.ListAdapter1

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.recyleview.*

class SearchActivity : AppCompatActivity() {
    val COUNT_KEY = "count_key"
    var arrayList = ArrayList<AllNotificationList1>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               Log.v("서브밋","서브밋")

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.v("바뀔때","바뀔때")
                return false
            }
        })


    }
}