package com.huluxia.assistance.pages.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
@Composable
fun Switch(
    checked: Boolean = false,
    disabled: Boolean = false,
    onChange: ((checked: Boolean) -> Unit)? = null
) {
    val offsetX by animateDpAsState(
        targetValue = if (checked) 26.dp else 2.dp,
        animationSpec = tween(durationMillis = 100),
        label = "SwitchAnimation"
    )

    Box(
        Modifier
            .size(50.dp, 26.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (checked) {
                    Color(0xFF1E88E5)
                } else {
                    Color(0xFFF3F3F3)
                }
            )
            .alpha(if (disabled) 0.7f else 1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                !disabled
            ) {
                onChange?.invoke(!checked)
            }
    ) {
        Box(
            Modifier
                .offset(offsetX, 2.dp)
                .size(22.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
        )
    }
}