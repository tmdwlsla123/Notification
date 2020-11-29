package com.example.customnotification.DataBase

import androidx.room.Embedded
import androidx.room.Relation

class AppNameAndAppDetail {
@Embedded
var appname: AppName? = null

    @Relation(parentColumn = "app_name", entityColumn = "name")
    var appdetail :AppDetail? =null

}
class AppNameAndAppDetail1 {
    @Embedded
    var appdetail :AppDetail? =null


    @Relation(parentColumn = "name", entityColumn = "app_name")
    var appname: AppName? = null

}