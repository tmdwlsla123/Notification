package com.example.customnotification.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.customnotification.DataBase.AppDetail
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.DataBase.AppNameAndAppDetail1

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private  val repository = ContactRepository(application)
    private  val contacts = repository.getAll()
//    private  val data = repository.getAll_detail()

    fun getAll(): LiveData<List<AppNameAndAppDetail>>{
        return this.contacts
    }
    fun getAll_detail(string: String): LiveData<List<AppNameAndAppDetail1>>{
        Log.v("레파지토리",repository.getAll_detail(string).toString())
        return this.repository.getAll_detail(string)
    }
    fun getAll_search(string: String?): List<AppNameAndAppDetail1> {
        return this.repository.getAll_search(string)
    }
    fun lockscreen(): LiveData<List<AppNameAndAppDetail1>>{
        return this.repository.lockscreen()
    }

}