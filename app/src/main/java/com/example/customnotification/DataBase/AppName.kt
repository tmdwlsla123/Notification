package com.example.customnotification.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_name")
data class AppName(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    @ColumnInfo(name = "app_name") var app_name: String?,
    @ColumnInfo(name = "app_icon") var app_icon: String?
) {
    constructor(): this(0,"", "")
}

