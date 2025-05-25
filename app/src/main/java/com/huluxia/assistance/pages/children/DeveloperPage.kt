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
import com.huluxia.assistance.DEVELOPER_SSL_IGNORE_KEY
import com.huluxia.assistance.pages.components.ChildItemSwitch

/**
 * @author: 迷路的小孩
 * @date: 2025/5/25
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val DEVELOPER_PAGE = "DeveloperPage"
fun NavGraphBuilder.developerPage(cls: ClassLoader, navController: NavHostController) {
    composable(DEVELOPER_PAGE) {
        DeveloperPage(cls, navController)
    }
}

@Composable
fun DeveloperPage(
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
                    text = "开发者选项",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "I'm a developer",
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
                            text = "网络", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "防抓包拦截",
                                description = "fuck！为什么不给抓包了？？",
                                key = DEVELOPER_SSL_IGNORE_KEY,
                                default = false
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }
}
