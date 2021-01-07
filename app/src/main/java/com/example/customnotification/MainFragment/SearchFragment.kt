package com.example.customnotification.MainFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import com.example.customnotification.R
import com.example.customnotification.SearchAdapter.SearchAdapter
import com.example.customnotification.ViewModel.ContactViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.recyleview.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var db: AppDB? = null
    var mContext: Context? = null
    var input_text: String? = null
    var searchAdapter: SearchAdapter? = null
    lateinit var noti_list_recyclerView: RecyclerView
    lateinit var noti_search: SearchView
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext()
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var parentview: View = inflater.inflate(R.layout.fragment_search, container, false)
        //        //클릭
        db = AppDB.getInstance(mContext!!)
        searchAdapter = SearchAdapter(arrayListOf(),mContext)
        val lm = LinearLayoutManager(mContext)
        noti_list_recyclerView = parentview.findViewById<View>(R.id.noti_list) as RecyclerView
        noti_list_recyclerView.adapter = searchAdapter
        noti_list_recyclerView.layoutManager = lm
        noti_search = parentview.findViewById<View>(R.id.search_view) as SearchView
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        noti_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.v("서브밋","서브밋")
                Log.v("서브밋",query)
//                getNamesFromDb(query)
//                input_text=query
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.v("바뀔때","바뀔때")
                    getNamesFromDb(query)
                    input_text=query

                return false
            }
        })
        return parentview
    }
    private fun getNamesFromDb(searchText: String?) {
        if(searchText.equals("")){
            searchAdapter!!.setContacts(listOf())
        }
        else{
            searchAdapter!!.setContacts(db!!.DAO().getAll_search("%$searchText%"))

//            contactViewModel.getAll_search("%$searchText%").observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//                    contacts -> searchAdapter!!.setContacts(contacts!!)
//                Log.e("메모리 누수 체크","체크하기")
//            })
//            val searchTextQuery = "%$searchText%"
//            db!!.DAO().getAll_search("%$searchText%")
//                .observe(this, object : Observer<List<AppNameAndAppDetail1>> {
//                    override fun onChanged(chapter: List<AppNameAndAppDetail1>?) {
//                        if (chapter == null) {
//                            Log.v("null","null")
//                            return
//                        }
//                        Log.v("rr",db!!.DAO().getAll_search(searchTextQuery).toString())
//                        Log.v("rr",chapter.toString())
//                        Log.v("귀모링","귀모링")
//
//                        searchAdapter!!.setContacts(chapter)
//                    }
//                })
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}