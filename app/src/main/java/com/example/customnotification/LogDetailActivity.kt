package com.example.customnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppDetail
import com.example.customnotification.LogAppListAdapter.ListAdapter
import com.example.customnotification.LogDetailAdapter.ListAdapter1
import com.example.customnotification.ViewModel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_noti.*
import kotlinx.android.synthetic.main.recyleview.*

class LogDetailActivity : AppCompatActivity() {
    val COUNT_KEY = "count_key"
    var arrayList = ArrayList<AppDetail>()
    var db: AppDB? = null
    var ListAdapter1: ListAdapter1? = null
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_detail)
        ListAdapter1 = ListAdapter1(arrayListOf(),this)
        var appname = intent.getStringExtra("appname")
        Log.v("인텐트 앱네임",appname)

        var AppCache = AppCache(this)
        var app_count = AppCache.getInt(COUNT_KEY,0)
        var position = intent.getIntExtra("position",0)
        Log.v("인텐트 포지션",position.toString())

        db = AppDB.getInstance(this)
//        val r = Runnable {
//            AppList = db.DAO().getAll_app_name()
//        }

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll_detail(appname).observe(this, Observer {
                contacts -> ListAdapter1!!.setContacts(contacts!!)
        })


        noti_list.adapter = ListAdapter1
        val lm = LinearLayoutManager(this)
        noti_list.layoutManager = lm
        noti_list.setHasFixedSize(true)
//        ListAdapter1.notifyDataSetChanged()
    }
}