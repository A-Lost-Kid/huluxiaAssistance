package com.huluxia.assistance

import android.annotation.SuppressLint
import android.os.Build
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.injectModuleAppResources
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.factory.registerModuleAppActivities
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.huluxia.assistance.hooks.hlxRequestHook
import com.huluxia.assistance.hooks.homeHook
import com.huluxia.assistance.hooks.menuHook
import com.huluxia.assistance.hooks.profileHook
import com.huluxia.assistance.hooks.resource.ResId
import com.huluxia.assistance.hooks.settingHook
import com.huluxia.assistance.hooks.topicHook
import de.robv.android.xposed.XposedHelpers

/**
 * @author: 迷路的小孩
 * @date: 2025/5/13
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
@InjectYukiHookWithXposed
object MainHook : IYukiHookXposedInit {
    @SuppressLint("ResourceType")

    override fun onInit() {
        YukiHookAPI.configs {
            debugLog {
                tag = "葫芦猪手"
                isEnable = true
                isRecord = false
                elements(TAG, PRIORITY, PACKAGE_NAME, USER_ID)
            }
            isDebug = BuildConfig.DEBUG
            isEnableModuleAppResourcesCache = true
            isEnableHookSharedPreferences = true
            isEnableDataChannel = true
        }
    }

    override fun onHook() = YukiHookAPI.encase {
        loadApp("com.huluxia.gametools", "com.huati") {
            try {
                onAppLifecycle {
                    onCreate {
                        YLog.info("injected successfully>>>>迷路的小孩.(3172471296)")
                        resources.injectModuleAppResources()
                        registerModuleAppActivities()

//                        反射出来数据储存类内的方法名
                        val reflectDataSource = "com.huluxia.data.d".toClass().method {
                            returnType = "com.huluxia.data.LoginUserInfo".toClass()
                        }.give()

                        val dataSource = XposedHelpers.callMethod(
                            "com.huluxia.data.d".toClass().getDeclaredConstructor().newInstance(),
                            reflectDataSource?.name
                        )
//                        初始化id获取tooler
                        appContext?.packageName?.let { ResId.init(it,appClassLoader) }

                        settingHook()
                        homeHook()
                        topicHook()
                        profileHook()
                        menuHook(dataSource)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && appContext?.prefs()
                                ?.native()?.getBoolean(CORE_DECRYPT_KEY, true) == true
                        ) {
                            hlxRequestHook()
                        }

                    }
                }

            } catch (e: Exception) {
                YLog.error(e.message.toString())
                throw e
            }

        }
    }
}