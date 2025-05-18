//package com.huluxia.assistance.hook
//
//import android.app.Activity
//import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
//import com.github.kyuubiran.ezxhelper.Log
//import com.github.kyuubiran.ezxhelper.finders.MethodFinder
//import de.robv.android.xposed.XposedHelpers
//import de.robv.android.xposed.callbacks.XC_LoadPackage
//import java.lang.reflect.Modifier
//
//
///**
// * @author: 迷路的小孩
// * @date: 2025/5/11
// * @signature: 善始者实繁，克终者盖寡。
// * @description:
// */
//class LoginHook(
//    val dataSource: Any
//) : BaseHook() {
//    override val name: String = "LoginHook"
//
//    override fun init(
//        lpparam: XC_LoadPackage.LoadPackageParam,
//    ) {
//        val qqLoginObj =
//            MethodFinder.fromClass("com.tencent.tauth.Tencent").filterByParamCount(3)
//                .filterByParamTypes(
//                    Activity::class.java,
//                    String::class.java,
//                    XposedHelpers.findClass("com.tencent.tauth.IUiListener", lpparam.classLoader)
//                ).filterByName("login").first()
//        Log.i("qqLoginHook对象>>>>$qqLoginObj")
//
//        qqLoginObj.createHook {
//            before { param ->
//                Log.i("Asasasas")
//                val activity = param.args[0] as Activity
////                val scope = param.args[1] as String
////                val listener = param.args[2]
////                XposedHelpers.callStaticMethod(
////                    XposedHelpers.findClass(
////                        "com.tencent.tauth.Tencent", lpparam.classLoader
////                    ), "a", "login_qrcode", "expires_time", scope, "qrcode", true
////                )
////                XposedHelpers.callMethod(
////                    XposedHelpers.findClass(
////                        "com.tencent.connect.auth.c", lpparam.classLoader
////                    ), "a", activity, scope, listener, true
////                )
//                activity.getIntent().putExtra("KEY_FORCE_QR_LOGIN", true);
//                return@before
//            }
//        }
//    }
//}