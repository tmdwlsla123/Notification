package com.example.customnotification.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_detail")
data class AppDetail(
    @PrimaryKey(autoGenerate = true)
    var D_id: Long?,
    @ColumnInfo(name = "packagename") var packagename: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "text") var text: String?,
    @ColumnInfo(name = "bigtext") var bigtext: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "picture") var picture: String?,
    @ColumnInfo(name = "update_idx") var idx: String?,
    @ColumnInfo(name = "screen_status") var screen_status: String?,
    @ColumnInfo(name = "lately_notifi") var lately_notifi: Long?
) {
    constructor(): this(0,"","", "","","","","","","",0)
}