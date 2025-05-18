package com.huluxia.assistance.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * @author: 迷路的小孩
 * @date: 2025/5/13
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun getVersionName(context: Context): String {
    return try {
        context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName.toString()
    } catch (e: PackageManager.NameNotFoundException) {
        "N/A"
    }
}

fun getVersionCode(context: Context): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        context.packageManager
            .getPackageInfo(context.packageName, 0)
            .longVersionCode
    } else {
        @Suppress("DEPRECATION")
        context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionCode.toLong()
    }
}
