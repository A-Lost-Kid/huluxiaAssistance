package com.huluxia.assistance.pages.children

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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.huluxia.assistance.MENU_POST_AUTH_SHOW_KEY
import com.huluxia.assistance.MENU_POST_COPY_POSTID_SHOW_KEY
import com.huluxia.assistance.MENU_POST_COPY_USERID_SHOW_KEY
import com.huluxia.assistance.MENU_POST_DELETE_SHOW_KEY
import com.huluxia.assistance.MENU_POST_EDIT_SHOW_KEY
import com.huluxia.assistance.MENU_POST_LOCK_SHOW_KEY
import com.huluxia.assistance.MENU_POST_MOVE_SHOW_KEY
import com.huluxia.assistance.MENU_POST_VERTICAL_SHOW_KEY
import com.huluxia.assistance.MENU_PROFILE_COPY_USERID_SHOW_KEY
import com.huluxia.assistance.pages.components.ChildItemSwitch


/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val MENU_PAGE = "MenuPage"
fun NavGraphBuilder.menuPage(cls: ClassLoader, navController: NavHostController) {
    composable(MENU_PAGE) {
        MenuPage(cls, navController)
    }
}

@Composable
fun MenuPage(
    cls: ClassLoader, navController: NavHostController
) {
    val context = LocalContext.current
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
                    text = "菜单选项",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "自定义你的菜单？",
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
                            text = "帖子菜单选项", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                .background(Color.White),
                        ) {
                            ChildItemSwitch(
                                context = context,
                                label = "移动帖子",
                                description = "仅页面显示 无实际作用",
                                key = MENU_POST_MOVE_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "锁定/解锁",
                                description = "仅页面显示 无实际作用",
                                key = MENU_POST_LOCK_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "版主认证",
                                description = "仅页面显示 无实际作用",
                                key = MENU_POST_AUTH_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "置顶帖子",
                                description = "貌似是我的帖子-置顶？",
                                key = MENU_POST_AUTH_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "编辑帖子",
                                description = "伪编辑他人的帖子 无实际作用",
                                key = MENU_POST_EDIT_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "删除帖子",
                                description = "仅页面显示 无实际作用",
                                key = MENU_POST_DELETE_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "复制帖子ID",
                                key = MENU_POST_COPY_POSTID_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "复制用户ID",
                                key = MENU_POST_COPY_USERID_SHOW_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "垂直展示选项",
                                description = "[*]实验性功能",
                                key = MENU_POST_VERTICAL_SHOW_KEY,
                                default = false
                            )
                        }
                    }
                }
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
                            text = "用户菜单选项", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                .background(Color.White),
                        ) {
                            ChildItemSwitch(
                                context = context,
                                label = "复制用户ID",
                                key = MENU_PROFILE_COPY_USERID_SHOW_KEY,
                                default = true
                            )
                        }
                    }
                }
            }
        }

    }
}
