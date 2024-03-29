package com.example.customnotification.MainFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.EventBus.MessageEvent
import com.example.customnotification.LogAppListAdapter.ListAdapter
import com.example.customnotification.R
import com.example.customnotification.ViewModel.ContactViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var noti_list_recyclerView: RecyclerView
    lateinit var noti_search: LinearLayout
    var mContext: Context? = null
    var ListAdapter: ListAdapter? = null
    var db: AppDB? = null
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
        var listItems: View = inflater.inflate(R.layout.fragment_noti, container, false)
        noti_list_recyclerView = listItems.findViewById<View>(R.id.noti_list) as RecyclerView
        noti_search = listItems.findViewById<View>(R.id.noti_search) as LinearLayout
        test2()
        val currentDateTime = Calendar.getInstance().time

            ListAdapter = ListAdapter(arrayListOf(),mContext)
//        Log.v("NotiNumber", AppCache.getAll().toString())
//        Log.v("NotiNumber", AppCache.getAll().toString())

        //오류 내기 전용 코드
//        search_button.setOnClickListener {  }
        //클릭
//        noti_search.search_button.setOnClickListener {
//            var intent = Intent(mContext, SearchActivity::class.java)
//            startActivity(intent)
//        }
        val lm = LinearLayoutManager(requireContext())
        noti_list_recyclerView.adapter = ListAdapter
        noti_list_recyclerView.layoutManager = lm
        noti_list_recyclerView.setHasFixedSize(true)
//        //클릭
        db = AppDB.getInstance(mContext!!)
//        val r = Runnable {
//            AppList = db.DAO().getAll_app_name()
//        }

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            contacts -> ListAdapter!!.setContacts(contacts!!)
        })


        // Inflate the layout for this fragment
        return listItems
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun test2() {

// Android 2.3 이상에서 사용 가능한 방식.

    }

    private fun getData(datetime: Long): String? {
        val formatter: DateFormat = SimpleDateFormat("yyyy.MM.dd")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = datetime
        return formatter.format(calendar.time)
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        Log.v("변경됨", event.toString())
//        for_list()

    }



}