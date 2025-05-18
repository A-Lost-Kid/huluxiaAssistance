package com.huluxia.assistance.hooks

import android.app.Application
import android.view.View
import android.widget.TextView
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.DISPLAY_PROFILE_AWAYLS_SHOW_IPADDR_KEY
import com.huluxia.assistance.DISPLAY_PROFILE_MORE_KEY
import com.huluxia.assistance.utils.getRelativeTime

/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.profileHook() {
    "com.huluxia.ui.profile.ProfileDetailActivity".toClass().method {
        name = "G1"
    }.hook {
        after {
            val ipTextView = instance.javaClass.field {
                name = "n2"
            }.get(instance).cast<TextView>()
            val lastLoginTimeTextView = instance.javaClass.field {
                name = "o2"
            }.get(instance).cast<TextView>()

            if (appContext?.prefs()?.native()?.getBoolean(DISPLAY_PROFILE_MORE_KEY, true) == true) {
                val profileInfo = instance.javaClass.field {
                    name = "S1"
                }.get(instance).current()

                val userID = profileInfo?.field {
                    name = "userID"
                }?.long()

                val lastLoginTime = profileInfo?.field {
                    name = "lastLoginTime"
                }?.long()

                val ipAddr = profileInfo?.field {
                    name = "ipAddr"
                }?.string()

                var showContent = "ID:$userID IP:$ipAddr"
                lastLoginTimeTextView?.visibility = View.GONE
                if (lastLoginTime != null) {
                    showContent += "\n[${u(lastLoginTime)}] ${getRelativeTime(lastLoginTime)}"
                }
                ipTextView?.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                ipTextView?.text = showContent
            }
            if (appContext?.prefs()?.native()
                    ?.getBoolean(DISPLAY_PROFILE_AWAYLS_SHOW_IPADDR_KEY, true) == true
            ) {
                ipTextView?.visibility = View.VISIBLE
            }
        }
    }
}

fun u(j: Long): String {
    val currentTimeMillis = (System.currentTimeMillis() - j) / 1000
    if (currentTimeMillis < 300) {
        return "刚刚"
    }
    if (currentTimeMillis < 3600) {
        return (currentTimeMillis / 60).toString() + "分钟前"
    }
    if (currentTimeMillis < 86400) {
        return (currentTimeMillis / 3600).toString() + "小时前"
    }
    if (currentTimeMillis < 172800) {
        return "昨天"
    }
    if (currentTimeMillis < 259200) {
        return "前天"
    }
    if (currentTimeMillis < 2592000) {
        return (currentTimeMillis / 86400).toString() + "天前"
    }
    if (currentTimeMillis < 31104000) {
        return (currentTimeMillis / 2592000).toString() + "月前"
    }
    return (currentTimeMillis / 31104000).toString() + "年前"
}