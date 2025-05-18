package com.huluxia.assistance.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun getRelativeTime(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}