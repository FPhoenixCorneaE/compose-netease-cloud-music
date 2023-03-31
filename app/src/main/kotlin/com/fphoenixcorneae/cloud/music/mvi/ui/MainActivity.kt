package com.fphoenixcorneae.cloud.music.mvi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.fphoenixcorneae.cloud.music.const.Constants
import com.fphoenixcorneae.cloud.music.mvi.ui.page.splash.SplashScreen
import com.fphoenixcorneae.cloud.music.mvi.ui.theme.NeteaseCloudMusicTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * @desc：
 * @date：2023/03/30 17:42
 */
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeteaseCloudMusicTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Constants.NavRoute.SPLASH,
                    ) {
                        composable(
                            route = Constants.NavRoute.SPLASH,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            SplashScreen(window = window, navController)
                        }
                        composable(
                            Constants.NavRoute.MAIN,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                            popEnterTransition = { fadeIn() },
                            popExitTransition = { fadeOut() },
                        ) {
                        }
                    }
                }
            }
        }
    }
}