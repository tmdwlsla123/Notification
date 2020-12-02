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

    @Query(
        "SELECT * FROM app_name LEFT JOIN (SELECT * FROM app_detail GROUP BY name) as b ON app_name.app_name = b.name ORDER BY D_id DESC;"
    )
    fun getAll_lately(): LiveData<List<AppNameAndAppDetail>>


    @Insert
    fun insertAll_app_name(data: AppName)

    @Query("SELECT EXISTS(SELECT * FROM app_name WHERE app_name = :id)")
    fun isRowIsExist(id: String): Boolean

    @Query("DELETE FROM app_name")
    fun deleteAll_app_name()

    @Query("SELECT * FROM app_detail")
    fun getAll_app_detail(): List<AppDetail>

//    @Query("SELECT * FROM app_detail WHERE name = :string")
//    fun getAll_app_detail_division(string: String): LiveData<List<AppDetail>>

    @Query("SELECT * FROM app_detail LEFT JOIN app_name ON app_detail.name = app_name.app_name WHERE name = :string ORDER BY D_id DESC")
    fun getAll_app_detail_division(string: String): LiveData<List<AppNameAndAppDetail1>>

    @Query("SELECT * FROM app_detail LEFT JOIN app_name ON app_detail.name = app_name.app_name WHERE text LIKE :string " +
            "OR app_name LIKE :string OR date LIKE :string OR title LIKE :string OR bigtext LIKE :string ORDER BY D_id DESC")
    fun getAll_search(string: String?): LiveData<List<AppNameAndAppDetail1>>

    @Insert
    fun insertAll_app_detail(data: AppDetail)

    @Query("DELETE FROM app_detail")
    fun deleteAll_app_detail()
}