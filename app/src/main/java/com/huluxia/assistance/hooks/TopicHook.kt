package com.huluxia.assistance.hooks

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.highcapable.yukihookapi.hook.factory.current
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.DISPLAY_TOPIC_COMMENT_MORE_KEY
import com.huluxia.assistance.DISPLAY_TOPIC_MORE_KEY
import com.huluxia.assistance.hooks.resource.ResId
import com.huluxia.assistance.hooks.resource.ResId.bbh
import com.huluxia.assistance.hooks.resource.ResId.bbj
import com.huluxia.assistance.hooks.resource.ResId.bbn
import com.huluxia.assistance.utils.getRelativeTime
import de.robv.android.xposed.XposedHelpers


/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun PackageParam.topicHook(){

    val dateTextViewId = XposedHelpers.getStaticIntField(bbh, "tv_date")
    val classTextViewId = XposedHelpers.getStaticIntField(bbh, "tv_class")

     val topicDetailItemAdapter = "com.huluxia.ui.itemadapter.topic.TopicDetailItemAdapter".toClass()
    topicDetailItemAdapter.method {
        name = "S"
    }.hook {
        after {
            val commentItem = topicDetailItemAdapter.method{
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
                    dateTextView.setOnClickListener {
                        val appDialog = XposedHelpers.getStaticIntField(bbn, "AppDialog")
                        val includeDialogOne =
                            XposedHelpers.getStaticIntField(bbj, "include_dialog_one")
                        val dialog = Dialog(thisContext, appDialog)
                        val dialogLayout = LayoutInflater.from(thisContext)
                            .inflate(includeDialogOne, null)
                        val textViewId = XposedHelpers.getStaticIntField(bbh, "tv_msg")
                        val textView = dialogLayout.findViewById<TextView>(textViewId)
                        textView.text = "这都被你发现了？\n我只想说林凡是我儿子"
                        dialog.setContentView(dialogLayout)
                        dialog.show()
                        val confirmId = XposedHelpers.getStaticIntField(bbh, "tv_confirm")
                        dialogLayout.findViewById<TextView>(confirmId).apply {
                            setOnClickListener {
                                dialog.dismiss()
                                dialog.cancel()
                            }
                        }
                    }
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
                    showContent += "\n发布时间[${u(createTime)}]${getRelativeTime(createTime)}"
                }

                dateTextView?.text = showContent
                classTextView?.text = "$categoryTitle($categoryID)"

                dateTextView?.visibility = View.VISIBLE
                classTextView?.visibility = View.VISIBLE
            }

        }
    }
}