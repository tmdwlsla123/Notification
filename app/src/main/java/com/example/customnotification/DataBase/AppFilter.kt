package com.example.customnotification.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_filter")
data class AppFilter(
    @PrimaryKey(autoGenerate = true)
    var F_id: Long?,
    @ColumnInfo(name = "packagename") var packagename: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "text") var text: String?,
    @ColumnInfo(name = "bigtext") var bigtext: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "idx") var idx: String?
)
{
    constructor(): this(0,"","", "","","","","")
}