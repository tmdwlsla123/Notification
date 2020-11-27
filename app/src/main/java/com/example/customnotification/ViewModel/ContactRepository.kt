package com.example.customnotification.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.customnotification.DataBase.AppDB
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.DataBase.DAO

class ContactRepository(application: Application) {
    private val contactDatabase = AppDB.getInstance(application)!!
    private val contactDao: DAO = contactDatabase.DAO()
    private val contacts: LiveData<List<AppName>> = contactDao.getAll_app_name()

    fun getAll(): LiveData<List<AppName>> {
        return contacts
    }

//    fun insert(contact: AppName) {
//        try {
//            val thread = Thread(Runnable {
//                contactDao.insert(contact) })
//            thread.start()
//        } catch (e: Exception) { }
//    }
//
//    fun delete(contact: AppName) {
//        try {
//            val thread = Thread(Runnable {
//                contactDao.delete(contact)
//            })
//            thread.start()
//        } catch (e: Exception) { }
//    }
}