package com.example.customnotification.SearchAdapter

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppNameAndAppDetail1
import kotlinx.android.synthetic.main.list.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class Holder2 (v: View, context: Context?) : RecyclerView.ViewHolder(v){
    var view : View = v
    var context: Context? = context

    fun bind(item: AppNameAndAppDetail1) {
        view.list_title.text = item.appdetail!!.title
        view.list_text.text = item.appdetail!!.text
        view.list_bigtext.text = item.appdetail!!.bigtext
        view.list_date.text = item.appdetail!!.date
//        var icon = BitmapConverter().StringToBitmap(item.icon)
        view.list_icon.setImageBitmap(loadImageFromStorage(item.appname!!.app_icon))
        view.list_appname.text = item.appname!!.app_name
        Log.v("데벵",item.appdetail.toString())
//        var picture = BitmapConverter().StringToBitmap(item.picture)
        view.list_picture.setImageBitmap(loadImageFromStorage(item.appdetail!!.picture))
//        var picture1 = BitmapConverter().StringToBitmap(item.picture1)
//        view.list_picture.setImageBitmap(picture1)
    }
    private fun loadImageFromStorage(file: String?) : Bitmap? {
        val wrapper = ContextWrapper(context)
        val path = wrapper.getDir("Images", Context.MODE_PRIVATE)
        var b: Bitmap? = null
        if(file.equals("null")){
            return b
        }
        try {

            val f = File(path, file)
            b = BitmapFactory.decodeStream(FileInputStream(f))

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return b
    }
}