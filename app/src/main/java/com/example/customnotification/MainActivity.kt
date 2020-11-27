package com.example.customnotification

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.customnotification.MainFragment.MainFragment
import com.example.customnotification.MainFragment.NotiFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    var firstfragment : Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("앱실행","확인")
        verifyStoragePermissions(this)
        a.setOnClickListener{
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,
                    MainFragment()
                )
                .commit();
        }
        b.setOnClickListener{
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,
                    NotiFragment()
                )
                .commit();
        }
        firstfragment = MainFragment()

        setDefaultFragment()



    }





    // Storage Permissions
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        val permission =
            ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }
    fun setDefaultFragment() {
        //화면에 보여지는 fragment를 추가하거나 바꿀 수 있는 객체를 만든다.
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        //첫번째로 보여지는 fragment는 firstFragment로 설정한다.
        transaction.add(R.id.fragment, firstfragment!!)
        //fragment의 변경사항을 반영시킨다.
        transaction.commit()
    }

}
