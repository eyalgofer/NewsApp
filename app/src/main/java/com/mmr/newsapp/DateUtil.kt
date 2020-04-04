package com.mmr.newsapp

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    // example: Tue, 14 Nov 2017 00:04
    fun prettifyDate(publishedAt: String) : String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val parsedDate : Date = dateFormat.parse(publishedAt)
        val formatter: DateFormat = SimpleDateFormat("EEE, d MMM yyyy hh:mm")
        val dateStr: String = formatter.format(parsedDate)
        return dateStr
    }
}