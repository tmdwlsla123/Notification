package com.example.customnotification.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.customnotification.DataBase.*

class ContactRepository(application: Application) {
    private val contactDatabase = AppDB.getInstance(application)!!
    private val contactDao: DAO = contactDatabase.DAO()
    private val contacts: LiveData<List<AppNameAndAppDetail>> = contactDao.getAll_lately()
//    private val data: LiveData<List<AppDetail>> = contactDao.getAll_app_detail_division()

    fun getAll(): LiveData<List<AppNameAndAppDetail>> {
        return contacts
    }
    fun getAll_detail(string: String): LiveData<List<AppNameAndAppDetail1>> {
        return contactDao.getAll_app_detail_division(string)
    }
    fun getAll_search(string: String?): LiveData<List<AppNameAndAppDetail1>> {
        return contactDao.getAll_search(string)
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