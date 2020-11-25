package com.example.customnotification.DataBase

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface DAO {
    @Query("SELECT * FROM app_name")
    fun getAll_app_name(): LiveData<List<AppName>>

    @Insert
    fun insertAll_app_name(data :AppName)

    @Query("SELECT EXISTS(SELECT * FROM app_name WHERE app_name = :id)")
    fun isRowIsExist(id : String) : Boolean

    @Query("DELETE FROM app_name")
    fun deleteAll_app_name()

    @Query("SELECT * FROM app_detail")
    fun getAll_app_detail(): List<AppDetail>

    @Insert
    fun insertAll_app_detail(data :AppDetail)

    @Query("DELETE FROM app_name")
    fun deleteAll_app_detail()
}