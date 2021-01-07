package com.example.customnotification.LogAppListAdapter

import android.R
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppName
import com.example.customnotification.DataBase.AppNameAndAppDetail
import com.example.customnotification.LogDetailActivity
import com.example.customnotification.UtilClass.UtilClass
import kotlinx.android.synthetic.main.list.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*


class Holder(v: View,c: Context?) : RecyclerView.ViewHolder(v){
    var view : View = v
    var context: Context? = c
    var cal : Calendar = Calendar.getInstance()
    var format :SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
    fun bind(item: AppNameAndAppDetail) {
        view.list_title.text = item.appdetail!!.title
        view.list_text.text = item.appdetail!!.text
        view.list_bigtext.text = item.appdetail!!.bigtext

        Log.v("시간",UtilClass.beforeTime(format.parse(item.appdetail!!.date)))
        view.list_date.text = UtilClass.beforeTime(format.parse(item.appdetail!!.date))
//        var icon = BitmapConverter().StringToBitmap(item.icon)
//        view.list_icon.setImageBitmap(icon)
        Log.v("포지션",item.appname.toString())
        view.list_icon.setImageBitmap(loadImageFromStorage(item.appname!!.app_icon))
        view.list_appname.text = item.appname!!.app_name
        view.setOnClickListener {
            Log.v("포지션",position.toString())
            val nextIntent = Intent(context, LogDetailActivity::class.java)
            nextIntent.putExtra("appname",item.appname!!.app_name)
            nextIntent.putExtra("position",position)
            context!!.startActivity(nextIntent)
        }
//        var picture = BitmapConverter().StringToBitmap(item.picture)
//        view.list_picture.setImageBitmap(picture)
//        var picture1 = BitmapConverter().StringToBitmap(item.picture1)
//        view.list_picture.setImageBitmap(picture1)
    }


//    fun loadFromInnerStorage(filename: String) :String{
//        val fileInputStream = context!!.openFileInput(filename)
//
//        return fileInputStream.reader(fileInputStream)
//    }
    private fun loadImageFromStorage(file: String?) : Bitmap? {
    val wrapper = ContextWrapper(context)
    val path = wrapper.getDir("Images",Context.MODE_PRIVATE)
    var b:Bitmap? = null
        try {

            val f = File(path, file)
             b = BitmapFactory.decodeStream(FileInputStream(f))

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    return b
    }

}