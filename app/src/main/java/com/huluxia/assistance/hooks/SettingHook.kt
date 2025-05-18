package com.huluxia.assistance.hooks

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.MainActivity
import com.huluxia.assistance.hooks.resource.ResId.bbh
import de.robv.android.xposed.XposedHelpers

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.settingHook() {
    val llDeveloper = XposedHelpers.getStaticIntField(bbh, "ll_developer")
    val tvDeveloper = XposedHelpers.getStaticIntField(bbh, "tv_developer")

    "com.huluxia.ui.settings.SettingsActivity".toClass().method {
        name = "onCreate"
    }.hook {
        before {
            val settingsActivity = instance as Activity
            Handler(Looper.getMainLooper()).post {
                try {
                    val developerLayout = settingsActivity.findViewById<LinearLayout>(llDeveloper)

                    developerLayout.visibility = View.VISIBLE
                    val developerTextView = settingsActivity.findViewById<TextView>(tvDeveloper)
                    developerTextView.text = "葫芦猪手（1.0.0）"
                    developerTextView.visibility = View.VISIBLE
                    developerTextView.setOnClickListener {
                        settingsActivity.startActivity(
                            Intent(settingsActivity, MainActivity::class.java)
                        )
                    }
                } catch (e: Exception) {
                    YLog.debug("settingsActivity>>>>Failed to modify settings UI")
                    throw e
                }
            }
        }
    }

    val aboutHookObj = "com.huluxia.module.topic.c".toClass().method {
        param(
            Long::class.java,
            Int::class.java,
            Int::class.java,
            Boolean::class.java,
            Int::class.java,
            Context::class.java
        )
    }
    aboutHookObj.hook {
        before {
            val postId = args[0] as Long
            if (postId == -1L) {
                val httpPathHook = "com.huluxia.http.request.a\$a".toClass().method {
                    paramCount = 1
                    param(String::class.java)
                }.hookAll {
                    before {
                        YLog.info(args[0].toString())
                        if (args[0].toString()
                                .contains("https://floor.huluxia.com/post/detail/ANDROID/")
                        ) {
                            args(index = 0).set("http://niu.wiki/huluxia.json")
                        }
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    httpPathHook.remove()
                }, 1000)
            }
        }
    }
}
