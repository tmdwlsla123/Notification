package com.example.customnotification.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppDetail::class,AppName::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun DAO() : DAO
    companion object{
        private var instance: AppDB? =null

        @Synchronized
        fun getInstance(context: Context): AppDB?{

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "database-contacts"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}