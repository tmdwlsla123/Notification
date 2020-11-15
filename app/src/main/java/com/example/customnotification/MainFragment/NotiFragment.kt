package com.example.customnotification.MainFragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.LogAppListAdapter.AllNotificationList
import com.example.customnotification.AppCache
import com.example.customnotification.LogAppListAdapter.ListAdapter
import com.example.customnotification.R
import kotlinx.android.synthetic.main.fragment_noti.*
import kotlinx.android.synthetic.main.fragment_noti.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
    lateinit var noti_list: RecyclerView
    lateinit var noti_search: LinearLayout
    var arrayList = ArrayList<AllNotificationList>()
    val COUNT_KEY = "count_key"
    var mContext: Context? = null



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
        noti_list = listItems.findViewById<View>(R.id.noti_list) as RecyclerView
        noti_search = listItems.findViewById<View>(R.id.noti_search) as LinearLayout
        test2()
        val currentDateTime = Calendar.getInstance().time
        var dateFormat1 =
            SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(currentDateTime)
        noti_search.textView2.text = dateFormat1

        var AppCache = AppCache(requireContext())

//        Log.v("NotiNumber", AppCache.getAll().toString())
//        Log.v("NotiNumber", AppCache.getAll().toString())

        noti_search.noit_calendar1.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    textView1.text = "${year}.${month + 1}.${dayOfMonth}"
                }
            }

            var builder = DatePickerDialog(mContext!!, date_listener, year, month, day)
            builder.show()

        }
        noti_search.noit_calendar2.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    textView2.text = "${year}.${month + 1}.${dayOfMonth}"
                }
            }

            var builder = DatePickerDialog(mContext!!, date_listener, year, month, day)
            builder.show()

        }

        var map: Map<String, *> = AppCache(context).getAll()

//        //클릭
//        noti_search.search_button.setOnClickListener {
//            arrayList.clear()
//            var search_value = noti_search.search_noti.text.toString()
//            val search_map: ArrayList<String> = ArrayList()
////            Log.v("흠",search_map.toString())
//            if (!search_value.equals("")) {
//                for ((key, value) in map) {
//
//                    if (-1 < value.toString().indexOf(search_value)) {
////                    Log.v("흠", "[key]:$key, [value]:$value")
//                        if (!search_map.contains(key.replace("[^0-9]".toRegex(), ""))) {
//                            search_map.add(key.replace("[^0-9]".toRegex(), ""))
//                        }
//                    }
//                }
//                Collections.sort(search_map)
//                Log.v("흠", search_map.toString())
//                for (i in 0..search_map.size - 1) {
//                    val AllNotificationList = AllNotificationList()
//                    AllNotificationList.title = AppCache.getString("title${search_map[i]}", "0")
//                    AllNotificationList.text = AppCache.getString("text${search_map[i]}", "0")
//                    AllNotificationList.date = AppCache.getString("date${search_map[i]}", "0")
//                    AllNotificationList.icon = AppCache.getString("icon${search_map[i]}", "0")
//                    AllNotificationList.appname = AppCache.getString("appname${search_map[i]}", "0")
//                    AllNotificationList.picture = AppCache.getString("picture${search_map[i]}", "0")
//
////                    Log.v("arraylist", AppCache.getString("text${search_map[i]}", "0"))
//                    arrayList.add(0, AllNotificationList)
//                }
//                val ListAdapter = ListAdapter(arrayList)
//                noti_list.adapter = ListAdapter
//                val lm = LinearLayoutManager(requireContext())
//                noti_list.layoutManager = lm
//                noti_list.setHasFixedSize(true)
//                ListAdapter.notifyDataSetChanged()
//            }
//            else{
//                start()
//            }
//
////            Log.v("흠",search_map.get(1).length.toString())
////            Log.v("흠",search_map.size.toString())
//        }
//        //클릭


        for (i in 1..AppCache.getInt(COUNT_KEY, 0)) {
            val AllNotificationList =
                AllNotificationList()
            AllNotificationList.title = AppCache.getString("title$i", "")

            AllNotificationList.text = AppCache.getString("text$i", "")

            AllNotificationList.bigtext = AppCache.getString("bigtext$i", "")

            if(AppCache.getString("bigtext$i", "").equals("null")){
                AllNotificationList.bigtext = ""
            }

            AllNotificationList.date = AppCache.getString("date$i", "")

            AllNotificationList.icon = AppCache.getString("icon$i", "")

            AllNotificationList.appname = AppCache.getString("appname$i", "")

//            AllNotificationList.picture = AppCache.getString("picture$i", "0")

//            AllNotificationList.picture1 = AppCache.getString("picture$i", "0")

//            Log.v("arraylist", AppCache.getString("title$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("text$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("date$i", "0"))

//            Log.v("arraylist", AppCache.getString("icon$i", "0"))
            arrayList.add(0, AllNotificationList)


        }

        val ListAdapter =
            ListAdapter(
                arrayList,
                activity
            )
        noti_list.adapter = ListAdapter
        val lm = LinearLayoutManager(requireContext())
        noti_list.layoutManager = lm
        noti_list.setHasFixedSize(true)
        ListAdapter.notifyDataSetChanged()
//        Log.v("arraylist", AllNotificationList)


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
        try {
            val packageManager: PackageManager = mContext!!.packageManager
            val installed = packageManager.getPackageInfo(
                mContext!!.packageName,
                0
            ).firstInstallTime
            Log.v("설치날짜", getData(installed))
            noti_search.textView1.text = getData(installed)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }

    private fun getData(datetime: Long): String? {
        val formatter: DateFormat = SimpleDateFormat("yyyy.MM.dd")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = datetime
        return formatter.format(calendar.time)
    }
//    private fun start(){
//        var AppCache = AppCache(requireContext())
//        for (i in 1..AppCache.getInt(COUNT_KEY, 0)) {
//            val AllNotificationList = AllNotificationList()
//            AllNotificationList.title = AppCache.getString("title$i", "0")
//
//            AllNotificationList.text = AppCache.getString("text$i", "0")
//
//            AllNotificationList.date = AppCache.getString("date$i", "0")
//
//            AllNotificationList.icon = AppCache.getString("icon$i", "0")
//
//            AllNotificationList.appname = AppCache.getString("appname$i", "0")
//
//            AllNotificationList.picture = AppCache.getString("picture$i", "0")
//
//            Log.v("arraylist", AppCache.getString("title$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("text$i", "0"))
//
//            Log.v("arraylist", AppCache.getString("date$i", "0"))
//
////            Log.v("arraylist", AppCache.getString("icon$i", "0"))
//            arrayList.add(0, AllNotificationList)
//
//
//        }
//
//        val ListAdapter = ListAdapter(arrayList)
//        noti_list.adapter = ListAdapter
//        val lm = LinearLayoutManager(requireContext())
//        noti_list.layoutManager = lm
//        noti_list.setHasFixedSize(true)
//        ListAdapter.notifyDataSetChanged()
//    }
}