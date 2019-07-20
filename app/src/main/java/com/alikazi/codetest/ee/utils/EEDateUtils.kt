package com.alikazi.codetest.ee.utils

import java.text.SimpleDateFormat
import java.util.*

object EEDateUtils {

    private const val DATE_FORMAT_HH_MM_AM_PM = "h:mma"

    fun formatDateToAmPm(date: Date): String {
        return SimpleDateFormat(DATE_FORMAT_HH_MM_AM_PM, Locale.UK).format(date)
    }
}