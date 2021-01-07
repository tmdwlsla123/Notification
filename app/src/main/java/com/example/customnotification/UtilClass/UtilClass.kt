package com.example.customnotification.UtilClass

import java.text.SimpleDateFormat
import java.util.*

class UtilClass {
    companion object{
        fun beforeTime(date: Date): String? {
            val c = Calendar.getInstance()
            val now = c.timeInMillis
            val dateM = date.time
            var gap = now - dateM
            var ret = ""

//        초       분   시
//        1000    60  60
            gap = (gap / 1000)
            val hour = gap / 3600
            gap = gap % 3600
            val min = gap / 60
            val sec = gap % 60
            ret = if (hour > 24) {
                SimpleDateFormat("yyyy.MM.dd HH:mm").format(date)
            } else if (hour > 0) {
                hour.toString() + "시간 전"
            } else if (min > 0) {
                min.toString() + "분 전"
            } else if (sec > 0) {
//            sec.toString() + "초 전"
                "지금"
            } else {
                SimpleDateFormat("yyyy.MM.dd HH:mm").format(date)
            }
            return ret
        }
    }

}