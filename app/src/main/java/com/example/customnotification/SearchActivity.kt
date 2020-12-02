package com.example.customnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.LogDetailAdapter.ListAdapter1
import com.example.customnotification.SearchAdapter.SearchAdapter
import com.example.customnotification.ViewModel.ContactViewModel

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.recyleview.*

class SearchActivity : AppCompatActivity() {
    var db: AppDB? = null
    var input_text: String? = null
    var searchAdapter: SearchAdapter? = null
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
//        searchAdapter = SearchAdapter(arrayListOf(),this)
//        val lm = LinearLayoutManager(this)
//        noti_list.adapter = searchAdapter
//        noti_list.layoutManager = lm
//        noti_list.setHasFixedSize(true)
//        //클릭
        db = AppDB.getInstance(this!!)
//        val r = Runnable {
//            AppList = db.DAO().getAll_app_name()
//        }


        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               Log.v("서브밋","서브밋")
                Log.v("서브밋",query)
                getNamesFromDb(query)
                input_text=query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.v("바뀔때","바뀔때")
                return false
            }
        })
//        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
//        contactViewModel.getAll_search(input_text).observe(this, androidx.lifecycle.Observer {
//                contacts -> searchAdapter!!.setContacts(contacts)
//        })


    }
    private fun getNamesFromDb(searchText: String?) {
        val searchTextQuery = "%$searchText%"
        db!!.DAO().getAll_search(searchTextQuery)
            .observe(this, object : Observer<List<AppNameAndAppDetail1>> {
                override fun onChanged(chapter: List<AppNameAndAppDetail1>?) {
                    if (chapter == null) {
                        Log.v("null","null")
                        return
                    }
                    Log.v("rr",db!!.DAO().getAll_search(searchTextQuery).toString())
                    Log.v("rr",chapter.toString())
                    Log.v("귀모링","귀모링")
                    val lm = LinearLayoutManager(application)
                    searchAdapter = SearchAdapter(arrayListOf(),application)
                    noti_list.adapter = searchAdapter
                    noti_list.layoutManager = lm
                    searchAdapter!!.setContacts(chapter)
                }
            })
    }
}