package com.huluxia.assistance.pages

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.toClass
import com.huluxia.assistance.CORE_DECRYPT_KEY
import com.huluxia.assistance.pages.children.ABOUT_PAGE
import com.huluxia.assistance.pages.children.DISPLAY_PAGE
import com.huluxia.assistance.pages.children.JUMP_PAGE
import com.huluxia.assistance.pages.children.MENU_PAGE
import com.huluxia.assistance.pages.components.ChildItem
import com.huluxia.assistance.pages.components.ChildItemSwitch
import com.huluxia.assistance.utils.getVersionCode
import com.huluxia.assistance.utils.getVersionName
import com.huluxia.assistance.utils.showToast
import com.kongzue.dialogx.dialogs.MessageDialog
import de.robv.android.xposed.XposedHelpers


/**
 * @author: 迷路的小孩
 * @date: 2025/5/13
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val MAIN_PAGE = "MainPage"
fun NavGraphBuilder.mainPage(cls: ClassLoader, navController: NavHostController) {
    composable(MAIN_PAGE) {
        MainPage(cls, navController)
    }
}

@Composable
fun MainPage(
    cls: ClassLoader, navController: NavHostController
) {
    val context = LocalContext.current
    val reflectDataSource = "com.huluxia.data.d".toClass(cls).method {
        returnType = "com.huluxia.data.LoginUserInfo".toClass(cls)
    }.give()

    val dataSource = XposedHelpers.callMethod(
        "com.huluxia.data.d".toClass(cls).getDeclaredConstructor().newInstance(),
        reflectDataSource?.name
    )
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Text(
                    text = "葫芦猪手",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "${getVersionName(context)}(${getVersionCode(context)})",
                    color = Color(0xff969696),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFAFAFA))
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "基本功能", color = Color(0xFF1E88E5), fontSize = 13.5.sp
                        )
                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        5.dp
                                    )
                                )
                                .background(Color.White)
                        ) {
                            ChildItemSwitch(
                                context = context,
                                label = "核心签名",
                                description = "常规LSP模块模式下打开此开关会占用小部分资源，故不推荐打开。无LSP框架用户当帖子内容乱码或登陆失败时需打开此功能！",
                                key = CORE_DECRYPT_KEY,
                                default = true
                            )
                            ChildItem(
                                label = "展示选项"
                            ) {
                                navController.navigate(DISPLAY_PAGE)
                            }
                            ChildItem(
                                label = "菜单选项"
                            ) {
                                navController.navigate(MENU_PAGE)
                            }
                            ChildItem(
                                label = "页面跳转"
                            ) {
                                navController.navigate(JUMP_PAGE)
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        5.dp
                                    )
                                )
                                .background(Color.White)
                        ) {
//                            ChildItem(
//                                label = "个人中心"
//                            ) {
//                                if (dataSource == null) {
//
//                                }else {
//
//                                }
//                            }
                            ChildItem(
                                label = "一键签到"
                            ) {
                                if (dataSource == null) {
                                    context.showToast("未登陆")
                                    "com.huluxia.ui.account.LoginActivity".toClass(cls)
                                        .also { clazz ->
                                            context.startActivity(
                                                Intent(context, clazz)
                                            )
                                        }
                                } else {
                                    val categoryIds = listOf<Long>(
                                        94L,
                                        43L,
                                        119L,
                                        81L,
                                        16L,
                                        44L,
                                        45L,
                                        96L,
                                        70L,
                                        111L,
                                        4L,
                                        29L,
                                        126L,
                                        71L,
                                        110L,
                                        107L,
                                        21L,
                                        115L,
                                        102L,
                                        3L,
                                        76L,
                                        57L,
                                        92L,
                                        98L,
                                        58L,
                                        82L,
                                        63L,
                                        22L,
                                        2L,
                                        108L,
                                        125L,
                                        6L,
                                        67L,
                                        68L,
                                        69L
                                    )
                                    categoryIds.forEach { categoryId ->
                                        XposedHelpers.callMethod(
                                            "com.huluxia.module.topic.c".toClass(
                                                cls
                                            ).getDeclaredConstructor().newInstance(),
                                            "j0",
                                            categoryId
                                        )
                                    }
                                    MessageDialog.show("执行完成", "签到任务已推送到队列！", "OK")
                                }
                            }
                            ChildItem(
                                label = "关于我们"
                            ) {
                                navController.navigate(ABOUT_PAGE)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        5.dp
                                    )
                                )
                                .background(Color.White)
                        ) {
                            ChildItem(
                                label = "Github"
                            ) {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    "https://github.com/A-Lost-Kid/com-huluxia-assistance".toUri()
                                )
                                context.startActivity(intent)
                            }
                        }
                    }

                }
            }
        }
    }
}
