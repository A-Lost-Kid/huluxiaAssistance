package com.huluxia.assistance.hooks

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.DISPLAY_HOOM_ROOT_RUN_KEY
import com.huluxia.assistance.hooks.resource.ResId.bbh
import de.robv.android.xposed.XposedHelpers

/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.homeHook() {
    val homeActivityClass = "com.huluxia.ui.home.HomeActivity".toClass()
    homeActivityClass.method {
        name = "onCreate"
    }.hook {
        after {
            val homeActivity = instance as Activity
            if (appContext?.prefs()?.native()
                    ?.getBoolean(DISPLAY_HOOM_ROOT_RUN_KEY, false) == true
            ) {
                XposedHelpers.getStaticIntField(bbh, "rly_root_run").let { it ->
                    homeActivity.findViewById<RelativeLayout>(it)?.visibility = View.GONE
                }
            }
        }
    }

//    homeActivityClass.method {
//        name = "M1"
//    }.hook {
//        after {
//                val tabArrayList = homeActivityClass.field{
//                    name = "j1"
//                }.get(instance).current()
//
//                tabArrayList?.method {
//                    name = "remove"
//                }?.call(0)
//        }
//    }

//    homeActivityClass.method {
//        name = "onCreate"
//    }.hook {
//        after {
//if (appContext?.prefs()?.native()
//                    ?.getBoolean(DISPLAY_HOOM_APP_STORE_KEY, false) == true
//            ) {
//
//
//
//                val tabArrayList1 = homeActivityClass.field{
//                    name = "k1"
//                }.get(instance).current()
//
//                tabArrayList1?.method {
//                    name = "remove"
//                }?.call(0)
//
//                YLog.error("${homeActivityClass.field{
//                    name = "j1"
//                }.get(instance)}")
//            }
//        }
//    }
}