package com.huluxia.assistance.pages.children

import android.content.Intent
import android.view.View
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
import com.github.kyuubiran.ezxhelper.misc.AndroidUtils.showToast
import com.highcapable.yukihookapi.hook.factory.toClass
import com.huluxia.assistance.pages.components.ChildItem
import com.kongzue.dialogx.dialogs.InputDialog
import com.kongzue.dialogx.dialogs.MessageDialog
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener


/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
const val JUMP_PAGE = "JumpPage"
fun NavGraphBuilder.jumpPage(cls: ClassLoader, navController: NavHostController) {
    composable(JUMP_PAGE) {
        JumpPage(cls, navController)
    }
}

@Composable
fun JumpPage(
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
                    text = "页面跳转",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "实验性功能",
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
                            text = "跳转", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "跳转到帖子"
                            ) {
                                InputDialog(
                                    "跳转到帖子",
                                    "输入帖子ID以跳转",
                                    "确定",
                                    "取消",
                                    ""
                                ).setCancelable(false).setOkButton(object :
                                        OnInputDialogButtonClickListener<InputDialog?> {
                                        override fun onClick(
                                            baseDialog: InputDialog?, v: View?, inputStr: String?
                                        ): Boolean {
                                            val postID = inputStr?.toLongOrNull()
                                            if (postID == null) {
                                                context.showToast("不合法的帖子ID")
                                                return false
                                            } else {
                                                "com.huluxia.ui.bbs.TopicDetailActivity".toClass(cls)
                                                    .also { clazz ->
                                                        context.startActivity(
                                                            Intent(context, clazz).apply {
                                                                putExtra("postID", postID)
                                                            })
                                                    }
                                                return false
                                            }
                                            return false
                                        }
                                    }).show()
                            }
                            ChildItem(
                                label = "跳转到用户"
                            ) {
                                InputDialog(
                                    "跳转到用户主页",
                                    "输入用户ID以跳转",
                                    "确定",
                                    "取消",
                                    ""
                                ).setCancelable(false).setOkButton(object :
                                        OnInputDialogButtonClickListener<InputDialog?> {
                                        override fun onClick(
                                            baseDialog: InputDialog?, v: View?, inputStr: String?
                                        ): Boolean {
                                            val userID = inputStr?.toLongOrNull()
                                            if (userID == null) {
                                                context.showToast("不合法的用户ID")
                                                return false
                                            } else {
                                                "com.huluxia.ui.profile.ProfileDetailActivity".toClass(cls)
                                                    .also { clazz ->
                                                        context.startActivity(
                                                            Intent(context, clazz).apply {
                                                                putExtra("USER_ID", userID)
                                                                putExtra("PROFILE_IS_OTHER", true)
                                                            })
                                                    }
                                                return false
                                            }
                                            return false
                                        }
                                    }).show()
                            }
                            ChildItem(
                                label = "跳转到版块"
                            ) {
                                InputDialog(
                                    "跳转到版块",
                                    "输入版块ID以跳转",
                                    "确定",
                                    "取消",
                                    ""
                                ).setCancelable(false).setOkButton(object :
                                        OnInputDialogButtonClickListener<InputDialog?> {
                                        override fun onClick(
                                            baseDialog: InputDialog?, v: View?, inputStr: String?
                                        ): Boolean {
                                            val categoryID = inputStr?.toLongOrNull()
                                            if (categoryID == null) {
                                                context.showToast("不合法的版块ID")
                                                return false
                                            } else {
                                                "com.huluxia.ui.bbs.SoftwareCategoryActivity".toClass(cls)
                                                    .also { clazz ->
                                                        context.startActivity(
                                                            Intent(context, clazz).apply {
                                                                putExtra("EXTRA_CATEGORY_ID", categoryID)
                                                            })
                                                    }
                                                return false
                                            }
                                            return false
                                        }
                                    }).show()
                            }
//                            ChildItem(
//                                label = "跳转到应用"
//                            ) {
//                                InputDialog(
//                                    "跳转到应用",
//                                    "输入应用ID以跳转",
//                                    "确定",
//                                    "取消",
//                                    ""
//                                ).setCancelable(false).setOkButton(object :
//                                        OnInputDialogButtonClickListener<InputDialog?> {
//                                        override fun onClick(
//                                            baseDialog: InputDialog?, v: View?, inputStr: String?
//                                        ): Boolean {
//                                            val gameID = inputStr?.toLongOrNull()
//                                            if (gameID == null) {
//                                                context.showToast("不合法的应用ID")
//                                                return false
//                                            } else {
//                                                "com.huluxia.ui.game.ResourceCuzAcitivity".toClass(cls)
//                                                    .also { clazz ->
//                                                        context.startActivity(
//                                                            Intent(context, clazz).apply {
//                                                                putExtra("appid", gameID)
//                                                            })
//                                                    }
//                                                return false
//                                            }
//                                            return false
//                                        }
//                                    }).show()
//                            }
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
                            text = "说明", color = Color(0xFF1E88E5), fontSize = 13.5.sp
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
                                label = "查看隐藏版块ID"
                            ) {
                                MessageDialog.show("隐藏的版块", "MC帖子: 67\n资源审核:68\n优秀资源: 69", "确定")
                            }
//                            ChildItem(
//                                label = "菜单选项"
//                            ) {
//                                MessageDialog.show("标题", "正文内容", "确定")
//                            }
                        }
                    }
                }
            }
        }

    }
}

