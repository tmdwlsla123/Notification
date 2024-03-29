package com.example.customnotification.MainFragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.LockScreen.LockScreen
import com.example.customnotification.R
import com.example.customnotification.LockScreenService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var mContext: Context? = null
    var db: AppDB? = null
    lateinit var noti_list: LinearLayout


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
        var mainview: View = inflater.inflate(R.layout.fragment_main, container, false)
        noti_list = mainview.findViewById<View>(R.id.fragment_main) as LinearLayout
        val lockScreenStatusPreferences by lazy {
            mContext!!.getSharedPreferences("LockScreenStatus", Context.MODE_PRIVATE)
        }

        // Inflate the layout for this fragment
        db = AppDB.getInstance(mContext!!)
        initLockScreenSwitch()
        noti_list.c.setOnClickListener {
            val dlg: AlertDialog.Builder = AlertDialog.Builder(
                context,
                android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth
            )
            dlg.setTitle("알림") //제목
            dlg.setMessage("정말 알림 기록을 모두 삭제하시겠습니까?") // 메시지
            dlg.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                db!!.DAO().deleteAll_app_name()
                db!!.DAO().deleteAll_app_detail()
            })
            dlg.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

            })
            dlg.show()

        }
//        noti_list.network.setOnClickListener {
//            (activity as MainActivity).sendReuqest()
//        }

        noti_list.lock_screen_switch_btn.setOnCheckedChangeListener { CompoundButton, onSwitch ->
//            setLockScreenStatus(onSwitch)
            lockScreenStatusPreferences.edit()?.run {
                putBoolean("LockScreenStatus", onSwitch)
                apply()
            }
            if (onSwitch) {
                LockScreen.active()
                Snackbar.make(
                    noti_list.lock_screen_switch_btn,
                    getString(R.string.lockscrenOn),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                LockScreen.deActivate()
                Snackbar.make(
                    noti_list.lock_screen_switch_btn,
                    getString(R.string.lockscrenOff),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

//        noti_list.now_start.setOnClickListener {
//            val intent = Intent(activity, LockScreenActivity::class.java)
//            startActivity(intent)
//        }
        noti_list.button.setOnClickListener {
            if (!isNotificationPermissionAllowed())
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            else
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            /**
             * Notification 접근 권한 체크 메서드
             * @return 접근권한이 있을 경우 true, 아니면 false
             */

        }

        return mainview
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    private fun isNotificationPermissionAllowed(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(mContext!!)
            .any { enabledPackageName ->
                enabledPackageName == requireActivity().packageName
            }
    }

    private fun initLockScreenSwitch() {
        val hasLockScreen = LockScreen.getLockScreenStatus()
        noti_list.lock_screen_switch_btn.isChecked = hasLockScreen
        if (hasLockScreen) {
            LockScreen.active()
        } else {
            LockScreen.deActivate()
        }
    }
//
//    private fun setLockScreenStatus(lockScreenStatus: Boolean) {
//        lockScreenStatusPreferences.edit()?.run {
//            putBoolean("LockScreenStatus", lockScreenStatus)
//            apply()
//        }
//    }
}
