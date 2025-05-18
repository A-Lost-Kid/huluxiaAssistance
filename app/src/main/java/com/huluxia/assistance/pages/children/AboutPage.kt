package com.huluxia.assistance.pages.children

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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.highcapable.yukihookapi.hook.factory.toClass
import com.huluxia.assistance.R
import com.huluxia.assistance.pages.components.ChildItem

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val ABOUT_PAGE = "AboutPage"
fun NavGraphBuilder.aboutPage(cls: ClassLoader, navController: NavHostController) {
    composable(ABOUT_PAGE) {
        AboutPage(cls, navController)
    }
}

@Composable
fun AboutPage(
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
                    text = "关于我们",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "迷路的小孩.",
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
                            text = "About", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                            ChildItem(
                                label = "模块功能介绍"
                            ) {
                                "com.huluxia.ui.bbs.TopicDetailActivity".toClass(cls)
                                    .also { clazz ->
                                        context.startActivity(
                                            Intent(context, clazz).apply {
                                                putExtra("postID", -1L)
                                            })
                                    }
                            }
                        }
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
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "赞助支持（你的支持是我前进的动力）",
                                color = Color.Black,
                                fontSize = 14.8.sp,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .fillMaxWidth()
                                    .height(0.5.dp)
                                    .background(Color(0xFFF3F3F3))
                            )
                            AsyncImage(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                model = R.drawable.reward,
                                contentDescription = "打赏一下"
                            )
                        }

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
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "开发历程",
                                color = Color.Black,
                                fontSize = 14.8.sp,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .fillMaxWidth()
                                    .height(0.5.dp)
                                    .background(Color(0xFFF3F3F3))
                            )
                            Text(
                                text = "其实也没啥好说的，毕竟从开发到上线第一版只用了一个晚上？\n制作不易，麻烦点个免费的关注（概率回关）",
                                color = Color.Black,
                                fontSize = 14.8.sp,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )
                        }
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
                            ChildItem(
                                label = "开发者主页（关注我）"
                            ) {
                                "com.huluxia.ui.profile.ProfileDetailActivity".toClass(cls)
                                    .also { clazz ->
                                        context.startActivity(
                                            Intent(context, clazz).apply {
                                                putExtra("USER_ID", 11250503L)
                                                putExtra("PROFILE_IS_OTHER", true)
                                            })
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}