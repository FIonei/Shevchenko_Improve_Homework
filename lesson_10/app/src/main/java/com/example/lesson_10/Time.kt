package com.example.lesson_10

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import java.text.SimpleDateFormat
import java.util.*

class Time(private val context: Context) {
    fun setCurrentBridgeImage(bridgeImage: ImageView, start: String, end: String) {
        val _pattern = context.getString(R.string.pattern_of_time)
        val pattern = SimpleDateFormat(_pattern, Locale.ENGLISH)
        val time1 = timeToIntMinutes(pattern.format(Date()))
        val time2start = timeToIntMinutes(start)
        val time2end = timeToIntMinutes(end)
        val imageId: Int
        if (time2start - time1 <= 60) {
            if (time2start - time1 >= 0) imageId = R.drawable.ic_brige_soon
            else if (time2end - time1 >= 0) imageId = R.drawable.ic_brige_late
            else imageId = R.drawable.ic_brige_normal
        } else imageId = R.drawable.ic_brige_normal
        bridgeImage.setImageDrawable(AppCompatResources.getDrawable(context, imageId))
    }

    private fun timeToIntMinutes(s: String): Int {
        val separated = s.split(':')
        return (separated[0].toInt() * 60 + separated[1].toInt())
    }

    fun uniteTime(item: BridgeInfoItem): String {
        var str = ""
        for (i in item.divorces!!) {
            str += (context.getString(R.string.time_format, i.start, i.end) + " ")
        }
        return str
    }
}