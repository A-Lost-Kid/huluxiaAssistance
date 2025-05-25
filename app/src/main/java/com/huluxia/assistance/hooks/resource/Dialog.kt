package com.huluxia.assistance.hooks.resource

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TextView
import com.huluxia.assistance.hooks.resource.ResId.bbh
import com.huluxia.assistance.hooks.resource.ResId.bbj
import com.huluxia.assistance.hooks.resource.ResId.bbn
import com.huluxia.assistance.utils.showToast
import de.robv.android.xposed.XposedHelpers

/**
 * @author: 迷路的小孩
 * @date: 2025/5/25
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
fun huluxiaDialogOne(
    thisContext: Context,
    content: String,
    confirmText: String = "确定",
    onConfirm: (dialog: Dialog) -> Unit
) {
    val appDialog = XposedHelpers.getStaticIntField(bbn, "AppDialog")
    val includeDialogOne = XposedHelpers.getStaticIntField(bbj, "include_dialog_one")
    val dialog = Dialog(thisContext, appDialog)
    val dialogLayout = LayoutInflater.from(thisContext).inflate(includeDialogOne, null)
    val textViewId = XposedHelpers.getStaticIntField(bbh, "tv_msg")
    val textView = dialogLayout.findViewById<TextView>(textViewId)
    textView.text = content
    dialog.setContentView(dialogLayout)
    dialog.show()
    val confirmId = XposedHelpers.getStaticIntField(bbh, "tv_confirm")
    dialogLayout.findViewById<TextView>(confirmId).apply {
        this.text = confirmText
        setOnClickListener {
            onConfirm(dialog)
        }
    }
}

fun huluxiaDialogTow(
    thisContext: Context,
    content: String,
    cancelText: String = "取消",
    confirmText: String = "确定",
    onCancel: (dialog: Dialog) -> Unit,
    onConfirm: (dialog: Dialog) -> Unit
) {
    val appDialog = XposedHelpers.getStaticIntField(bbn, "AppDialog")
    val includeDialogTwo = XposedHelpers.getStaticIntField(bbj, "include_dialog_two")
    val dialog = Dialog(thisContext, appDialog)
    val dialogLayout = LayoutInflater.from(thisContext).inflate(includeDialogTwo, null)
    val textViewId = XposedHelpers.getStaticIntField(bbh, "tv_msg")
    val textView = dialogLayout.findViewById<TextView>(textViewId)
    textView.text = content
    textView.setOnLongClickListener {
        huluxiaDialogThree(
            thisContext = thisContext,
            title = "和你说个秘密",
            content = "林凡是我儿子",
            cancelText = "爸爸",
            otherText = "爸爸",
            confirmText = "爸爸",
            onCancel = {
                thisContext.showToast("总是向你索取却不曾说谢谢你")
                it.dismiss()
            },
            onOther = {
                thisContext.showToast("直到长大以后才懂得你不容易")
                it.dismiss()
            }
        ){
            thisContext.showToast("每次离开总是装作轻松的样子")
            it.dismiss()
        }
        true
    }
    dialog.setContentView(dialogLayout)
    dialog.show()
    val cancelId = XposedHelpers.getStaticIntField(bbh, "tv_cancel")
    dialogLayout.findViewById<TextView>(cancelId).apply {
        setOnClickListener {
            onCancel(dialog)
        }
    }
    val confirmId = XposedHelpers.getStaticIntField(bbh, "tv_confirm")
    dialogLayout.findViewById<TextView>(confirmId).apply {
        this.setTextColor(Color.RED)
        this.text = confirmText
        setOnClickListener {
            onConfirm(dialog)
        }
    }
}


fun huluxiaDialogThree(
    thisContext: Context,
    title: String,
    content: String,
    cancelText: String = "取消",
    otherText: String = "忽略",
    confirmText: String = "确定",
    onCancel: (dialog: Dialog) -> Unit,
    onOther: (dialog: Dialog) -> Unit,
    onConfirm: (dialog: Dialog) -> Unit
) {
    val appDialog = XposedHelpers.getStaticIntField(bbn, "AppDialog")
    val includeDialogThree = XposedHelpers.getStaticIntField(bbj, "include_dialog_three")
    val dialog = Dialog(thisContext, appDialog)
    val dialogLayout = LayoutInflater.from(thisContext).inflate(includeDialogThree, null)
    val titleId = XposedHelpers.getStaticIntField(bbh, "tv_title")
    val titleTextView = dialogLayout.findViewById<TextView>(titleId)
    titleTextView.text = title
    val textViewId = XposedHelpers.getStaticIntField(bbh, "tv_msg")
    val textView = dialogLayout.findViewById<TextView>(textViewId)
    textView.text = content
    dialog.setContentView(dialogLayout)
    dialog.show()
    val cancelId = XposedHelpers.getStaticIntField(bbh, "tv_cancel")
    dialogLayout.findViewById<TextView>(cancelId).apply {
        this.text = cancelText
        setOnClickListener {
            onCancel(dialog)
        }
    }
    val otherId = XposedHelpers.getStaticIntField(bbh, "tv_other")
    dialogLayout.findViewById<TextView>(otherId).apply {
        this.text = otherText
        this.setTextColor(Color.RED)
        setOnClickListener {
            onOther(dialog)
        }
    }
    val confirmId = XposedHelpers.getStaticIntField(bbh, "tv_confirm")
    dialogLayout.findViewById<TextView>(confirmId).apply {
        this.text = confirmText
        setOnClickListener {
            onConfirm(dialog)
        }
    }
}