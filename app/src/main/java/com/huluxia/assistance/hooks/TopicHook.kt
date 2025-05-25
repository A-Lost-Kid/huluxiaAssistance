package com.huluxia.assistance.hooks

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.highcapable.yukihookapi.hook.factory.current
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_COMMENT_MORE_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_MORE_COLOR_RED_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_MORE_KEY
import com.huluxia.assistance.hooks.resource.ResId.bbh
import com.huluxia.assistance.hooks.resource.ResId.bbj
import com.huluxia.assistance.hooks.resource.ResId.bbn
import com.huluxia.assistance.hooks.resource.huluxiaDialogOne
import com.huluxia.assistance.hooks.resource.huluxiaDialogTow
import com.huluxia.assistance.utils.getRelativeTime
import com.huluxia.assistance.utils.showToast
import de.robv.android.xposed.XposedHelpers


/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.topicHook() {

    val dateTextViewId = XposedHelpers.getStaticIntField(bbh, "tv_date")
    val classTextViewId = XposedHelpers.getStaticIntField(bbh, "tv_class")
    val publishTimeId = XposedHelpers.getStaticIntField(bbh,"publish_time")

    val messageItemAdapter= "com.huluxia.ui.itemadapter.message.MessageItemAdapter".toClass()
    messageItemAdapter.method{
        name = "p"
        paramCount = 4
    }.hook{
        after {
            val thisContext = instance.javaClass.field {
                name = "c"
            }.get(instance).cast<Context>()
            val view = args[0] as View
            val commentItem = args[1]?.current()

            val commentID = commentItem?.field {
                name = "commentID"
            }?.long()

            val createTime = commentItem?.field {
                name = "createTime"
            }?.long()
            if (appContext?.prefs()?.native()
                    ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_KEY, true) == true
            ) {
                view.findViewById<TextView>(publishTimeId).apply {
                    if (createTime != null) {
                        this?.text = "[${u(createTime)}] ${getRelativeTime(createTime)}"
                    }
                    this?.text = this.text.toString() + "\n评论ID:$commentID"
                    this?.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    this?.setOnClickListener {
                        createTime?.let { timestamp ->
                            if (thisContext != null) {
                                huluxiaDialogTow(
                                    thisContext,
                                    "评论ID:$commentID\n发布时间:${getRelativeTime(createTime)}",
                                    "取消",
                                    "复制评论ID",
                                    {
                                        it.dismiss()
                                    }) {
                                    internalCopy(appClassLoader, thisContext, commentID.toString())
                                    thisContext.showToast("复制成功")
                                    it.dismiss()
                                }
                            }
                        }
                    }
                    if (appContext?.prefs()?.native()
                            ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY, false) == true
                    ) {
                        this?.setTextColor(Color.RED)
                    }
                }
            }
        }
    }
    val profileCommentItemAdapter = "com.huluxia.ui.itemadapter.profile.ProfileCommentItemAdapter".toClass()
    profileCommentItemAdapter.method{
        name = "getView"
        returnType = View::class.java
    }.hook{
        after {
            val thisContext = instance.javaClass.field {
                name = "a"
            }.get(instance).cast<Context>()

            val commentItem = (instance as ArrayAdapter<*>).getItem(args[0] as Int)?.current()

            val commentID = commentItem?.field {
                name = "commentID"
            }?.long()

            val createTime = commentItem?.field {
                name = "createTime"
            }?.long()

            if (appContext?.prefs()?.native()
                    ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_KEY, true) == true
            ) {
                val view = result as View
                view.findViewById<TextView>(publishTimeId).apply {
//                    this?.text = "sodjosdjsoj"
                    if (createTime != null) {
                        this?.text = "[${u(createTime)}] ${getRelativeTime(createTime)}"
                    }
                    this?.text = this.text.toString() + "\n评论ID:$commentID"
                    this?.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    this?.setOnClickListener {
                        createTime?.let { timestamp ->
                            if (thisContext != null) {
                                huluxiaDialogTow(thisContext,"评论ID:$commentID\n发布时间:${getRelativeTime(createTime)}","取消","复制评论ID",{
                                    it.dismiss()
                                }){
                                    internalCopy(appClassLoader, thisContext, commentID.toString())
                                    thisContext.showToast("复制成功")
                                    it.dismiss()
                                }
                            }
                        }
                    }
                    if (appContext?.prefs()?.native()
                            ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY, false) == true
                    ) {
                        this?.setTextColor(Color.RED)
                    }
                }
            }
        }
    }

    val topicDetailItemAdapter = "com.huluxia.ui.itemadapter.topic.TopicDetailItemAdapter".toClass()
    topicDetailItemAdapter.method {
        name = "S"
    }.hook {
        after {
            val thisContext = instance.javaClass.field {
                name = "d"
            }.get(instance).cast<Activity>()
            val commentItem = topicDetailItemAdapter.method {
                name = "getItem"
            }.get(instance).call(args[1] as Int)?.current()

            val commentID = commentItem?.field {
                name = "commentID"
            }?.long()

            val createTime = commentItem?.field {
                name = "createTime"
            }?.long()
            if (appContext?.prefs()?.native()
                    ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_KEY, true) == true
            ) {
                args[0]?.current()?.field {
                    name = "i"
                }?.cast<TextView>().apply {
                    if (createTime != null) {
                        this?.text = "[${u(createTime)}] ${getRelativeTime(createTime)}"
                    }
                    this?.text = this.text.toString() + "\n评论ID:$commentID"
                    this?.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    this?.setOnClickListener {
                        createTime?.let { timestamp ->
                            if (thisContext != null) {
                                huluxiaDialogTow(thisContext,"评论ID:$commentID\n发布时间:${getRelativeTime(createTime)}","取消","复制评论ID",{
                                    it.dismiss()
                                }){
                                    internalCopy(appClassLoader, thisContext, commentID.toString())
                                    thisContext.showToast("复制成功")
                                    it.dismiss()
                                }
                            }
                        }
                    }
                    if (appContext?.prefs()?.native()
                            ?.getBoolean(DISPLAY_TOPIC_COMMENT_MORE_COLOR_RED_KEY, false) == true
                    ) {
                        this?.setTextColor(Color.RED)
                    }
                }
            }
        }
    }

    val topicDetailTitle = "com.huluxia.ui.bbs.TopicDetailTitle".toClass()
    topicDetailTitle.method {
        paramCount = 2
        param(
            "com.huluxia.data.topic.TopicItem".toClass(), Boolean::class.java
        )
    }.hook {
        after {
            val linearLayout = instance as LinearLayout
            val thisContext = linearLayout.context
            val dateTextView = linearLayout.findViewById<TextView>(dateTextViewId)
            val classTextView = linearLayout.findViewById<TextView>(classTextViewId)
            if (appContext?.prefs()?.native()?.getBoolean(DISPLAY_TOPIC_MORE_KEY, true) == true) {
                val topicItem = args[0]?.current()

                val postID = topicItem?.field {
                    name = "postID"
                }?.long()

                val createTime = topicItem?.field {
                    name = "createTime"
                }?.long()

                val categoryItem = topicItem?.field {
                    name = "category"
                }?.current()

                val categoryTitle = categoryItem?.field {
                    name = "title"
                }?.string()

                val categoryID = categoryItem?.field {
                    name = "categoryID"
                }?.long()

                var showContent = "帖子ID:$postID"
                if (createTime != null) {
                    showContent += "\n发布时间:[${u(createTime)}]${getRelativeTime(createTime)}"
                }
                dateTextView.setOnClickListener {
                    createTime?.let { timestamp -> huluxiaDialogTow(thisContext,"帖子ID:$postID\n发布时间:${getRelativeTime(timestamp)}","取消","复制帖子ID",{
                        it.dismiss()
                    }){
                        internalCopy(appClassLoader, appContext!!, postID.toString())
                        appContext!!.showToast("复制成功")
                        it.dismiss()
                    } }
                }
                dateTextView?.text = showContent
                classTextView?.text = "$categoryTitle($categoryID)"
                if (appContext?.prefs()?.native()
                        ?.getBoolean(DISPLAY_TOPIC_MORE_COLOR_RED_KEY, false) == true
                ) {
                    dateTextView?.setTextColor(Color.RED)
                    classTextView?.setTextColor(Color.RED)
                }

                dateTextView?.visibility = View.VISIBLE
                classTextView?.visibility = View.VISIBLE
            }

        }
    }
}