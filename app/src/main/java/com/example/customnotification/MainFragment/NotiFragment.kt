package com.example.customnotification.MainFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.*
import kotlinx.android.synthetic.main.activity_lock_screen.*
import kotlinx.android.synthetic.main.fragment_noti.*
import java.util.ArrayList

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
    lateinit var noti_list : RecyclerView
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
//    override  fun onAttach(context : Context){
//        super.onAttach(context)
//        requireContext()
//         mContext = context
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listItems: View = inflater.inflate(R.layout.fragment_noti, container, false)
        noti_list = listItems.findViewById<View>(R.id.noti_list) as RecyclerView
        val AppCache = AppCache(requireContext())

        Log.v("NotiNumber", AppCache.getInt(COUNT_KEY, 0).toString())
        Log.v("NotiNumber", AppCache.getAll().toString())


        for (i in 1..AppCache.getInt(COUNT_KEY, 0)) {
            val AllNotificationList = AllNotificationList()
            AllNotificationList.title = AppCache.getString("title$i", "0")

            AllNotificationList.text = AppCache.getString("text$i", "0")

            AllNotificationList.date = AppCache.getString("date$i", "0")

            Log.v("arraylist", AppCache.getString("title$i", "0"))

            Log.v("arraylist", AppCache.getString("text$i", "0"))

            Log.v("arraylist", AppCache.getString("date$i", "0"))
            arrayList.add(0,AllNotificationList)


        }
        val ListAdapter = ListAdapter(arrayList)
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
}