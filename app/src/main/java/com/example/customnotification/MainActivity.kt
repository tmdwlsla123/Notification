package com.example.customnotification

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppFilter
import com.example.customnotification.MainFragment.MainFragment
import com.example.customnotification.MainFragment.NotiFragment
import com.example.customnotification.MainFragment.SearchFragment
import com.example.customnotification.Retrofit2.ApiInterface
import com.example.customnotification.Retrofit2.HttpClient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap


class MainActivity : AppCompatActivity() {
    var firstfragment: Fragment? = null
    var mainFragment: Fragment? = null
    var searchFragment: Fragment? = null
    var db: AppDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, NotificationCollectorMonitorService::class.java))
        Log.v("앱실행", "확인")
        db = AppDB.getInstance(this!!)
        verifyStoragePermissions(this)
        var fragmentManaget = getSupportFragmentManager()
        firstfragment = NotiFragment()
        setDefaultFragment()
        bottom_navigationbar.setOnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.b ->{
                    if (mainFragment == null) {
                        mainFragment = MainFragment()
                        fragmentManaget.beginTransaction().add(R.id.fragment,mainFragment!!).commit()
                    }
                    if (firstfragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(firstfragment!!).commit()
                    }
                    if(mainFragment !=null){
                        fragmentManaget.beginTransaction()
                            .show(mainFragment!!).commit()
                    }
                    if (searchFragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(searchFragment!!).commit()
                    }
                }
                R.id.a ->{
                    if (firstfragment == null) {
                        firstfragment = NotiFragment()
                        fragmentManaget.beginTransaction().add(R.id.fragment, firstfragment!!).commit()
                    }

                    if (mainFragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(mainFragment!!).commit()
                    }

                    if (firstfragment != null) {
                        fragmentManaget.beginTransaction()
                            .show(firstfragment!!).commit()
                    }
                    if (searchFragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(searchFragment!!).commit()
                    }
                }
                R.id.c ->{
                    if(searchFragment==null){
                        searchFragment = SearchFragment()
                        fragmentManaget.beginTransaction().add(R.id.fragment, searchFragment!!).commit()
                    }
                    if (mainFragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(mainFragment!!).commit()
                    }

                    if (firstfragment != null) {
                        fragmentManaget.beginTransaction()
                            .hide(firstfragment!!).commit()
                    }
                    if (searchFragment != null) {
                        fragmentManaget.beginTransaction()
                            .show(searchFragment!!).commit()
                    }
                }
            }
            true
        }
//        b.setOnClickListener {
//            if (mainFragment == null) {
//                mainFragment = MainFragment()
//                fragmentManaget.beginTransaction().add(R.id.fragment,mainFragment!!).commit()
//            }
//            if (firstfragment != null) {
//                fragmentManaget.beginTransaction()
//                    .hide(firstfragment!!).commit()
//            }
//            if(mainFragment !=null){
//                fragmentManaget.beginTransaction()
//                    .show(mainFragment!!).commit()
//            }
//
//
////            getSupportFragmentManager().beginTransaction()
////                .replace(
////                    R.id.fragment,
////                    MainFragment()
////                )
////                .commit();
//        }
//        a.setOnClickListener {
//            if (firstfragment == null) {
//                firstfragment = NotiFragment()
//                fragmentManaget.beginTransaction().add(R.id.fragment, firstfragment!!).commit()
//            }
//
//            if (mainFragment != null) {
//                fragmentManaget.beginTransaction()
//                    .hide(mainFragment!!).commit()
//            }
//
//            if (firstfragment != null) {
//                fragmentManaget.beginTransaction()
//                    .show(firstfragment!!).commit()
//            }
//
//
////            getSupportFragmentManager().beginTransaction()
////                .replace(
////                    R.id.fragment,
////                    NotiFragment()
////                )
////                .commit();
//        }
        if (db!!.DAO().isRow_update_idx_EXISTS()) {
//            sendReuqest()
        }


    }

    fun sendReuqest() {
        val gson = Gson()
        val url =
            "noti" //ex) 요청하고자 하는 주소가 http://10.0.2.2/login 이면 String url = login 형식으로 적으면 됨
        var data: String? = null
        val api: ApiInterface = HttpClient.getRetrofit()!!.create(ApiInterface::class.java)
        val params =
            HashMap<String, String?>()
        data = gson.toJson(db!!.DAO().get_not_updated())

        params["key"] = data
        Log.e("data", data);
        val call: Call<String> = api.requestPost(url, params)

        // 비동기로 백그라운드 쓰레드로 동작

        // 비동기로 백그라운드 쓰레드로 동작
        call.enqueue(object : Callback<String> {
            // 통신성공 후 텍스트뷰에 결과값 출력
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
//서버에서 넘겨주는 데이터는 response.body()로 접근하면 확인가능
                Log.e("retrofit2", response.body().toString())
                Log.e("retrofit2", response.toString())


                try {
                    val jsonArray = JSONArray(response.body())
                    for (i in 0..jsonArray.length() - 1) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        var packagename = jsonObject.getString("packagename")
                        var app_name = jsonObject.getString("app_name")
                        var app_title = jsonObject.getString("app_title")
                        var app_text = jsonObject.getString("app_text")
                        var app_bigtext = jsonObject.getString("app_bigtext")
                        var app_date = jsonObject.getString("app_date")
                        var app_idx = jsonObject.getString("idx")
                        var app_class = AppFilter(
                            null,
                            packagename,
                            app_name,
                            app_title,
                            app_text,
                            app_bigtext,
                            app_date,
                            app_idx
                        )
                        if (!db!!.DAO().isRow_filter_EXISTS(app_idx)) {
                            db!!.DAO().insertAll_app_filter(app_class)
                        }

                    }
                    db!!.DAO().update_idx_column_update()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }


            }

            // 통신실패
            override fun onFailure(
                call: Call<String>,
                t: Throwable
            ) {
                Log.v("retrofit2", "error : $t")
            }
        })
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
