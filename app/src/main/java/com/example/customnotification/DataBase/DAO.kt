package com.example.customnotification.DataBase

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    @Query("SELECT * FROM app_name")
    fun getAll_app_name(): LiveData<List<AppName>>

    @Query(
        "SELECT * FROM app_name LEFT JOIN (SELECT * FROM app_detail GROUP BY name) as b ON app_name.app_name = b.name ORDER BY D_id DESC;"
    )
    fun getAll_lately(): LiveData<List<AppNameAndAppDetail>>

//    @Query("SELECT app_detail.* FROM (SELECT * FROM app_detail AS C WHERE C.screen_status = 0 ORDER BY C.D_id DESC) AS app_detail  LEFT JOIN (SELECT * FROM app_detail WHERE screen_status = 0 GROUP BY packagename ORDER BY D_id DESC) AS A ON app_detail.packagename = A.packagename ORDER BY A.D_id DESC")
//    fun lockscreen(): LiveData<List<AppNameAndAppDetail1>>

    @Query("SELECT * FROM (SELECT * FROM app_detail GROUP BY packagename ORDER BY D_id DESC) AS app_detail WHERE app_detail.screen_status = 0")
    fun lockscreen(): LiveData<List<AppNameAndAppDetail1>>



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
    fun getAll_search(string: String?): List<AppNameAndAppDetail1>

    @Insert
    fun insertAll_app_detail(data: AppDetail): Long

    @Query("DELETE FROM app_detail")
    fun deleteAll_app_detail()
    @Query("SELECT EXISTS(SELECT * FROM app_detail WHERE update_idx = '0')")
    fun isRow_update_idx_EXISTS(): Boolean

    @Query("SELECT * FROM app_detail WHERE D_id > (SELECT D_id FROM app_detail WHERE update_idx = '1')")
    fun get_lately_noti_data(): List<AppDetail>

    @Insert
    fun insertAll_app_filter(data: AppFilter)

    @Query("SELECT EXISTS(SELECT * FROM app_filter WHERE idx = :idx)")
    fun isRow_filter_EXISTS(idx: String):Boolean

    @Query("SELECT * FROM app_detail WHERE update_idx = '0'")
    fun get_not_updated() : List<AppDetail>

    @Query("UPDATE app_detail SET update_idx = 1 WHERE update_idx = 0")
    fun update_idx_column_update()

    @Query("UPDATE app_detail SET lately_notifi = 1 WHERE NOT D_id =:id AND packagename =:packagename ")
    fun update_lately_notifi(id: Long,packagename: String)

    @Query("UPDATE app_detail SET screen_status = 1 WHERE packagename =:id")
    fun update_screen_status(id: String)
}