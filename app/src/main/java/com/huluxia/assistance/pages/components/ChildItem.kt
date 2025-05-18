package com.huluxia.assistance.pages.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.highcapable.yukihookapi.hook.factory.prefs
import com.huluxia.assistance.R
import com.huluxia.assistance.utils.showToast

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
@Composable
fun ChildItem(
    label: String, description: String = "", onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick.invoke()
        }
        .padding(vertical = 20.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = label, color = Color.Black, fontSize = 14.8.sp
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description, color = Color.Black.copy(0.6f), fontSize = 12.8.sp
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.arrow_right),
            contentDescription = null
        )
    }
}

@Composable
fun ChildItemSwitch(
    context: Context, label: String, description: String = "", key: String, default: Boolean = false
) {
    var checked by remember { mutableStateOf(context.prefs().native().getBoolean(key, default)) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = label, color = Color.Black, fontSize = 14.8.sp
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description, color = Color.Black.copy(0.6f), fontSize = 12.8.sp
                )
            }
        }
        Switch(
            checked = checked, disabled = false
        ) { localChecked ->
            checked = localChecked
            context.prefs().native().edit { putBoolean(key, checked) }
            context.showToast("重启软件后生效")
        }
    }
}