package com.huluxia.assistance.hooks

import android.content.Context
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.factory.toClass
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.huluxia.assistance.MENU_POST_COPY_POSTID_SHOW_KEY
import com.huluxia.assistance.MENU_POST_COPY_USERID_SHOW_KEY
import com.huluxia.assistance.MENU_POST_DELETE_SHOW_KEY
import com.huluxia.assistance.MENU_POST_EDIT_SHOW_KEY
import com.huluxia.assistance.MENU_POST_LOCK_SHOW_KEY
import com.huluxia.assistance.MENU_POST_MOVE_SHOW_KEY
import com.huluxia.assistance.MENU_POST_TOP_SHOW_KEY
import com.huluxia.assistance.MENU_POST_VERTICAL_SHOW_KEY
import com.huluxia.assistance.MENU_PROFILE_COPY_USERID_SHOW_KEY
import com.huluxia.assistance.hooks.resource.ResId.bbc
import com.huluxia.assistance.utils.showToast
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.menuHook(
    dataSource: Any?
) {
    var hookedPostMenu = false
    var hookedPorfileMenu = false
    val bd = "com.huluxia.framework.base.widget.dialog.b\$e".toClass()
    val dialogB = "com.huluxia.framework.base.widget.dialog.b".toClass()
    val menuObj = "com.huluxia.utils.UtilsMenu".toClass().method {
        modifiers {
            isPublic
            isStatic
        }
        paramCount = 5
    }
    YLog.debug("话题菜单对象>>>>${menuObj.javaClass}")

    menuObj.hook {
        before {
            YLog.debug("话题菜单对象>>>>开始构建")
            val context = args[0] as Context
            val topicItem = args[1]
            val isRes = args[2] as Boolean
            val menuListener = args[3]

            val menuValue = "com.huluxia.utils.UtilsMenu\$MENU_VALUE".toClass()

            val topicItemClass = topicItem?.javaClass
            val postIDField = (topicItemClass?.getDeclaredField("postID"))
            postIDField?.isAccessible = true
            postIDField?.get(topicItem)

            val topicUserId = XposedHelpers.callMethod(topicItem, "getUserInfo")
                ?.let { XposedHelpers.callMethod(it, "getUserID") }

            var sessionUserId = 0L
            if (dataSource != null) {
                val sessionClass = dataSource.javaClass
                val userIDField = (sessionClass.getDeclaredField("userID"))
                userIDField.isAccessible = true
                sessionUserId = userIDField.get(dataSource) as Long
            }


            val colorId = XposedHelpers.callStaticMethod(
                "com.simple.colorful.d".toClass(),
                "c",
                context,
                XposedHelpers.getStaticIntField(bbc, "normalPrimaryGreen")
            )
            val redColorId = XposedHelpers.callStaticMethod(
                "com.simple.colorful.d".toClass(),
                "c",
                context,
                XposedHelpers.getStaticIntField(bbc, "textColorRed")
            )

            val mSortArrayList = ArrayList::class.java.getDeclaredConstructor().newInstance()
            if (!isRes) {
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    "评论",
                    menuValue.getDeclaredField("COMMENT").get(null).let { it as Enum<*> }.ordinal,
                    colorId
                )
            }

            addComplaintOption(
                bd,
                mSortArrayList,
                "送葫芦",
                menuValue.getDeclaredField("SEND_HULU").get(null).let { it as Enum<*> }.ordinal,
                colorId
            )
            if (!isRes && topicUserId != sessionUserId) {
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    "举报",
                    menuValue.getDeclaredField("REPORT_TOPIC").get(null)
                        .let { it as Enum<*> }.ordinal,
                    colorId
                )
            }

            addComplaintOption(
                bd,
                mSortArrayList,
                "分享到社交网络",
                menuValue.getDeclaredField("SHAREWIXIN").get(null).let { it as Enum<*> }.ordinal,
                colorId
            )
            if (appContext?.prefs()?.native()?.getBoolean(MENU_POST_MOVE_SHOW_KEY, true) == true) {
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    "移动话题",
                    menuValue.getDeclaredField("MOVETOPIC").get(null).let { it as Enum<*> }.ordinal,
                    colorId
                )
            }
            if (appContext?.prefs()?.native()?.getBoolean(MENU_POST_LOCK_SHOW_KEY, true) == true) {
                if (XposedHelpers.callMethod(topicItem, "getState") == 3) {
                    addComplaintOption(
                        bd,
                        mSortArrayList,
                        "解锁主题",
                        menuValue.getDeclaredField("UNLOCK_TOPIC").get(null)
                            .let { it as Enum<*> }.ordinal,
                        colorId
                    )
                } else {
                    addComplaintOption(
                        bd,
                        mSortArrayList,
                        "锁定主题",
                        menuValue.getDeclaredField("LOCK_TOPIC").get(null)
                            .let { it as Enum<*> }.ordinal,
                        colorId
                    )
                }
            }


            if (topicUserId == sessionUserId || appContext?.prefs()?.native()?.getBoolean(
                    MENU_POST_TOP_SHOW_KEY, true
                ) == true
            ) {
                val menuTop = if (XposedHelpers.callMethod(
                        topicItem, "isPostTop"
                    ) as Boolean
                ) "取消置顶" else "置顶"
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    menuTop,
                    menuValue.getDeclaredField("TOP_TOPIC").get(null).let { it as Enum<*> }.ordinal,
                    colorId
                )
            }

            if (!isRes && ((topicUserId == sessionUserId) || appContext?.prefs()?.native()
                    ?.getBoolean(
                        MENU_POST_EDIT_SHOW_KEY, true
                    ) == true)
            ) {
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    "编辑话题",
                    menuValue.getDeclaredField("EDITTOPIC").get(null).let { it as Enum<*> }.ordinal,
                    colorId
                )
            }
            if (!isRes && ((topicUserId == sessionUserId) || appContext?.prefs()?.native()
                    ?.getBoolean(
                        MENU_POST_DELETE_SHOW_KEY, true
                    ) == true)
            ) {
                if (XposedHelpers.callMethod(topicItem, "getState") != 3) {
                    addComplaintOption(
                        bd,
                        mSortArrayList,
                        "删除话题",
                        menuValue.getDeclaredField("REMOVE_TOPIC").get(null)
                            .let { it as Enum<*> }.ordinal,
                        colorId
                    )
                }
            }

            addComplaintOption(
                bd,
                mSortArrayList,
                "复制全文",
                menuValue.getDeclaredField("COPY_TEXT").get(null).let { it as Enum<*> }.ordinal,
                colorId
            )

            if (!isRes && XposedHelpers.callMethod(
                    topicItem,
                    "getState"
                ) == 1 && appContext?.prefs()?.native()
                    ?.getBoolean(MENU_POST_COPY_POSTID_SHOW_KEY, true) == true
            ) {
                val resId = if (XposedHelpers.callMethod(topicItem, "isAuthention") as Boolean) {
                    XposedHelpers.getStaticObjectField(
                        "com.huluxia.bbs.b\$m".toClass(), "topic_unauth"
                    )
                } else {
                    XposedHelpers.getStaticObjectField(
                        "com.huluxia.bbs.b\$m".toClass(), "topic_auth"
                    )
                }
                val authAction = context.resources.getString(resId as Int)
                addComplaintOption(
                    bd,
                    mSortArrayList,
                    authAction,
                    menuValue.getDeclaredField("AUTHENTICATE_TOPIC").get(null)
                        .let { it as Enum<*> }.ordinal,
                    colorId
                )
            }
            if (appContext?.prefs()?.native()
                    ?.getBoolean(MENU_POST_COPY_POSTID_SHOW_KEY, true) == true
            ) {
                addComplaintOption(bd, mSortArrayList, "复制帖子ID", 1001, redColorId)
            }
            if (appContext?.prefs()?.native()
                    ?.getBoolean(MENU_POST_COPY_USERID_SHOW_KEY, true) == true
            ) {
                addComplaintOption(bd, mSortArrayList, "复制用户ID", 1002, redColorId)
            }

            val columnNum = if (mSortArrayList.size >= 6 && appContext?.prefs()?.native()
                    ?.getBoolean(MENU_POST_VERTICAL_SHOW_KEY, false) == false
            ) 2 else 1
            val menuDialog = XposedHelpers.newInstance(
                dialogB, context, menuListener, XposedHelpers.callStaticMethod(
                    "com.simple.colorful.d".toClass(), "o"
                ), columnNum
            )

            XposedHelpers.callMethod(menuDialog, "f", mSortArrayList)
            result = menuDialog

        }
    }

    val topicDetailObj = "com.huluxia.ui.bbs.TopicDetailActivity".toClass().method {
        paramCount = 2
//        name = "a"
        param(BooleanType, "com.huluxia.data.topic.CommentItem".toClass())
    }

    YLog.debug("帖子详情Activity对象>>>>$topicDetailObj")

    topicDetailObj.hook {
        before {
            val topicDetailActivity13 = "com.huluxia.ui.bbs.TopicDetailActivity\$l".toClass()
            val parameterTypes = arrayOf(
                Int::class.java
            )
            if (!hookedPostMenu) {
                val callback = object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        val inIndex = param?.args?.get(0) as? Int
                        YLog.debug("$inIndex")
                        if (inIndex == 1001) {
//                            val topicItem = XposedHelpers.getObjectField(param.thisObject, "b")
//                                .let { XposedHelpers.getObjectField(it, "z1") }
//                            val topicItemClass = topicItem?.javaClass
//                            val postIDField = (topicItemClass?.getDeclaredField("postID"))
//                            postIDField?.isAccessible = true
//                            val postID = postIDField?.get(topicItem)

                            val postID = param.thisObject.javaClass.field {
                                name = "b"
                            }.get(param.thisObject).current()?.field {
                                name = "z1"
                            }?.current()?.field {
                                name = "postID"
                            }?.long()
                            internalCopy(appClassLoader, this, postID.toString())
                            param.result = null
                            return
                        } else if (inIndex == 1002) {
                            val userID = param.thisObject.javaClass.field {
                                name = "b"
                            }.get(param.thisObject).current()?.field {
                                name = "z1"
                            }?.current()?.field {
                                name = "user"
                            }?.current()?.field {
                                name = "userID"
                            }?.long()
                            internalCopy(appClassLoader, this, userID.toString())
                            param.result = null
                            return
                        }
                    }

                    override fun afterHookedMethod(param: MethodHookParam?) {
                        super.afterHookedMethod(param)
                    }
                }
                hookedPostMenu = true
                XposedHelpers.findAndHookMethod(
                    topicDetailActivity13, "a", *parameterTypes, callback
                )
            }

        }
    }

    val profileMenuObj = "com.huluxia.utils.UtilsMenu".toClass().method {
        name = "t"
        modifiers {
            isPublic
            isStatic
        }
        paramCount = 3
        param(
            Context::class.java,
            BooleanType,
            "com.huluxia.framework.base.widget.dialog.b\$c".toClass()
        )
    }
    YLog.debug("用户详情菜单对象>>>>${profileMenuObj.javaClass}")

    profileMenuObj.hook {
        before {
            YLog.debug("用户详情菜单对象>>>>开始构建用户详情菜单")
            val context = args[0] as Context
            val isInBlacklist = args[1] as Boolean
            val menuListener = args[2]

            val colorProfileDetailMoreAction = XposedHelpers.callStaticMethod(
                "com.simple.colorful.d".toClass(), "c", context, XposedHelpers.getStaticObjectField(
                    "com.huluxia.bbs.b\$c".toClass(), "colorProfileDetailMoreAction"
                )
            )

            val mSortArrayList = ArrayList::class.java.getDeclaredConstructor().newInstance()
            addComplaintOption(
                bd, mSortArrayList, "举报", 0, colorProfileDetailMoreAction
            )
            if (isInBlacklist) {
                addComplaintOption(
                    bd, mSortArrayList, "移出黑名单", 2, colorProfileDetailMoreAction
                )
            } else {
                addComplaintOption(
                    bd, mSortArrayList, "加入黑名单", 1, colorProfileDetailMoreAction
                )
            }
            if (appContext?.prefs()?.native()
                    ?.getBoolean(MENU_PROFILE_COPY_USERID_SHOW_KEY, true) == true
            ) {
                addComplaintOption(
                    bd, mSortArrayList, "复制用户ID", 3, colorProfileDetailMoreAction
                )
            }


            val menuDialog = XposedHelpers.newInstance(
                dialogB, context, menuListener, XposedHelpers.callStaticMethod(
                    "com.simple.colorful.d".toClass(), "o"
                ), 1
            )
            XposedHelpers.callMethod(menuDialog, "f", mSortArrayList)
            result = menuDialog
        }
    }

    val profileClickObj = "com.huluxia.ui.profile.ProfileDetailActivity".toClass().method {
        paramCount = 0
        name = "M1"
    }

    profileClickObj.hook {
        before {
            YLog.debug("用户详情菜单对象>>>>用户详情菜单>>OnClick")
            val profileDetailActivity = "com.huluxia.ui.profile.ProfileDetailActivity\$i".toClass()
            val parameterTypes = arrayOf(
                Int::class.java
            )
            val profileDetailActivityThis = instance
            if (!hookedPorfileMenu) {
                val callback = object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        val inIndex = param?.args?.get(0) as? Int
                        val userID = profileDetailActivityThis.javaClass.field {
                            name = "S1"
                        }.get(profileDetailActivityThis).current()?.field {
                            name = "userID"
                        }?.long()

                        if (inIndex == 0) {
                            if (userID.toString() == "11250503") {
                                val mContext = profileDetailActivityThis.javaClass.field {
                                    name = "A2"
                                }.get(profileDetailActivityThis).cast<Context>()
                                mContext?.showToast("就你也配举报👴？滚！")
                                param.result = null
                                return
                            }
                        } else if (inIndex == 3) {
                            internalCopy(appClassLoader, this, userID.toString())
                            param.result = null
                            return
                        }
                    }


                    override fun afterHookedMethod(param: MethodHookParam?) {
                        super.afterHookedMethod(param)
                    }
                }
                hookedPorfileMenu = true
                XposedHelpers.findAndHookMethod(
                    profileDetailActivity, "a", *parameterTypes, callback
                )
            }

        }
    }

}

private fun internalCopy(classLoader: ClassLoader?, xc: XC_MethodHook, text: String) {
    "com.huluxia.framework.base.utils.n".toClass(classLoader).method {
        paramCount = 1
        param(String::class.java)
    }.get(xc).call(text)
}

private fun addComplaintOption(bd: Class<*>, list: Any, text: String, value: Int, color: Any) {
    try {
        val item = XposedHelpers.newInstance(bd, text, value, color)
        XposedHelpers.callMethod(list, "add", item)
    } catch (e: Exception) {
        YLog.error("菜单构建>>>>构建菜单失败[$text](${e.message})")
    }
}