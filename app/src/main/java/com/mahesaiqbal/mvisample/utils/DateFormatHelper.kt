package com.mahesaiqbal.mvisample.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatHelper {
    fun getFormattedDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val parsedToDate = inputFormat.parse(date)
        return parsedToDate?.let { outputFormat.format(it) }.orEmpty()
    }
}