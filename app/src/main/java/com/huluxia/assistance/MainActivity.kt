package com.huluxia.assistance

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.highcapable.yukihookapi.hook.xposed.parasitic.activity.base.ModuleAppCompatActivity
import com.kongzue.dialogx.DialogX

/**
 * @author: 迷路的小孩
 * @date: 2025/5/13
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
class MainActivity() : ModuleAppCompatActivity() {

    override val moduleTheme get() = R.style.Theme_HuluAssistance

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_HuluAssistance)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ) { true })

        setContent {
            DialogX.init(this)
            val cls = applicationContext.classLoader
            val navController = rememberNavController()
            Startup(cls, navController)
        }
    }
}