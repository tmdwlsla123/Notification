package com.example.customnotification.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.customnotification.DataBase.AppName

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private  val repository = ContactRepository(application)
    private  val contacts = repository.getAll()

    fun getAll(): LiveData<List<AppName>>{
        return this.contacts
    }
}