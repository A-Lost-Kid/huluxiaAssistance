package com.huluxia.assistance.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun Context.showToast(text: String) {
    Handler(Looper.getMainLooper()).post {
        val toastLength = if (text.length >= 10) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(this, text, toastLength).show()
    }
}