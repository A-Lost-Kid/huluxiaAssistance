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
import com.huluxia.assistance.DISPLAY_HOOM_ROOT_RUN_KEY
import com.huluxia.assistance.DISPLAY_MESSAGE_COMMENT_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_MESSAGE_COMMENT_MORE_KEY
import com.huluxia.assistance.DISPLAY_PROFILE_AWAYLS_SHOW_IPADDR_KEY
import com.huluxia.assistance.DISPLAY_PROFILE_COMMENT_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_PROFILE_COMMENT_MORE_KEY
import com.huluxia.assistance.DISPLAY_PROFILE_MORE_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_COMMENT_MORE_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_MORE_KEY
import com.huluxia.assistance.pages.components.ChildItemSwitch


/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val DISPLAY_PAGE = "DisplayPage"
fun NavGraphBuilder.displayPage(cls: ClassLoader, navController: NavHostController) {
    composable(DISPLAY_PAGE) {
        DisplayPage(cls, navController)
    }
}

@Composable
fun DisplayPage(
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
                    text = "展示选项",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "自定义我的葫芦侠",
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
                            text = "首页", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "隐藏修改器",
                                description = "nm！不给用还放在那里干嘛？？",
                                key = DISPLAY_HOOM_ROOT_RUN_KEY,
                                default = false
                            )
//                            ChildItemSwitch(
//                                context = context,
//                                label = "隐藏应用商店",
//                                description = "我不需要软件下载器",
//                                key = DISPLAY_HOOM_APP_STORE_KEY,
//                                default = false
//                            )
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
                            text = "个人主页", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "始终显示IP",
                                description = "服务器获取不到自己的上线时间？？",
                                key = DISPLAY_PROFILE_AWAYLS_SHOW_IPADDR_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "主页展示详细信息",
                                description = "ID、IP、上线时间",
                                key = DISPLAY_PROFILE_MORE_KEY,
                                default = true
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
                            text = "帖子详情", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "帖子展示详细信息",
                                description = "ID、 时间精细、版块ID",
                                key = DISPLAY_TOPIC_MORE_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "评论展示详细信息",
                                description = "评论ID、 时间精细",
                                key = DISPLAY_TOPIC_COMMENT_MORE_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "我的评论展示详细信息",
                                description = "评论ID、 时间精细",
                                key = DISPLAY_PROFILE_COMMENT_MORE_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "消息评论展示详细信息",
                                description = "评论ID、 时间精细",
                                key = DISPLAY_MESSAGE_COMMENT_MORE_KEY,
                                default = true
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "帖子信息文本高显",
                                key = DISPLAY_TOPIC_MORE_COLOR_RED_KEY,
                                default = false
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "评论信息文本高显",
                                key = DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY,
                                default = false
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "我的评论信息文本高显",
                                key = DISPLAY_PROFILE_COMMENT_MORE_COLOR_RED_KEY,
                                default = false
                            )
                            ChildItemSwitch(
                                context = context,
                                label = "消息评论信息文本高显",
                                key = DISPLAY_MESSAGE_COMMENT_MORE_COLOR_RED_KEY,
                                default = false
                            )
                        }
                    }
                }
            }
        }

    }
}
