package com.example.customnotification

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


class BitmapConverter {
    /*
     * String형을 BitMap으로 변환시켜주는 함수
     * */
    fun StringToBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }

    /*
     * Bitmap을 String형으로 변환
     * */
    fun BitmapToString(bitmap: Bitmap?): String {
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 70, baos)
        val bytes: ByteArray = baos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /*
     * Bitmap을 byte배열로 변환
     * */
    fun BitmapToByteArray(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos)
        return baos.toByteArray()
    }
}