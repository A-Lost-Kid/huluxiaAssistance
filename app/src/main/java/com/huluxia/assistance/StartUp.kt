package com.huluxia.assistance

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.huluxia.assistance.pages.MAIN_PAGE
import com.huluxia.assistance.pages.children.aboutPage
import com.huluxia.assistance.pages.children.developerPage
import com.huluxia.assistance.pages.children.displayPage
import com.huluxia.assistance.pages.children.jumpPage
import com.huluxia.assistance.pages.children.menuPage
import com.huluxia.assistance.pages.mainPage

/**
 * @author: 迷路的小孩
 * @date: 2025/5/13
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
@Composable
fun Startup(
    cls: ClassLoader,
    navController: NavHostController, startDestination: String = MAIN_PAGE
) {
    NavHost(
        navController = remember { navController },
        startDestination = startDestination,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth: Int -> fullWidth }, animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth: Int -> -fullWidth }, animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth: Int -> -fullWidth }, animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth: Int -> fullWidth }, animationSpec = tween(300)
            )
        }) {
        mainPage(
            cls = cls,
            navController = navController
        )
        jumpPage(
            cls = cls,
            navController = navController
        )
        aboutPage(
            cls = cls,
            navController = navController
        )
        menuPage(
            cls = cls,
            navController = navController
        )
        displayPage(
            cls = cls,
            navController = navController
        )
        developerPage(
            cls = cls,
            navController = navController
        )
    }
}