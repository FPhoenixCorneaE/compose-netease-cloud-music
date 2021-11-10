package com.fphoenixcorneae.netease.cloudmusic.main.mvvm.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.postDelayed
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.jetpackmvvm.compose.base.activity.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @desc：全版本兼容 SplashScreen
 * @date：2021/11/09 16:42
 */
class SplashScreenCompatActivity : BaseActivity(), SplashScreen.OnExitAnimationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        initView()
        initListener()
        initData()
        splashScreen.setKeepVisibleCondition {
            true
        }
        splashScreen.setOnExitAnimationListener(this)
        // 延迟2秒, 使得开屏页能够停留2秒钟
        lifecycleScope.launch {
            delay(2_000)
            splashScreen.setKeepVisibleCondition {
                false
            }
        }
    }

    override fun initView() {
        val controller = ViewCompat.getWindowInsetsController(window.decorView)
        controller?.apply {
            // 隐藏状态栏
            hide(WindowInsetsCompat.Type.statusBars())
            // 隐藏虚拟按键
            hide(WindowInsetsCompat.Type.navigationBars())
            //
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun initListener() {
    }

    override fun initData() {
        setRealContent {
            SplashScreen()
        }
    }

    override fun toolbarVisible(): Boolean {
        return false
    }

    override fun onSplashScreenExit(splashScreenViewProvider: SplashScreenViewProvider) {
        // 如果在 themes.xml中 配置了：静态背景, 改成 true 看效果
        val flag = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R || flag) {
            // 使用alpha透明度动画过渡
            val splashScreenView = splashScreenViewProvider.view
            val endAlpha = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) 0f else -2f
            val alphaObjectAnimator = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, endAlpha)
            alphaObjectAnimator.duration = 500L
            alphaObjectAnimator.interpolator = FastOutLinearInInterpolator()
            alphaObjectAnimator.doOnEnd {
                splashScreenViewProvider.remove()
            }
            alphaObjectAnimator.start()
            return
        }

        // 下面是所有使用动态背景的，我们让中心图标做一个动画然后离开
        val splashScreenView = splashScreenViewProvider.view
        val iconView = splashScreenViewProvider.iconView
        val isCompatVersion = Build.VERSION.SDK_INT < Build.VERSION_CODES.R
        val slideUp = ObjectAnimator.ofFloat(
            iconView,
            View.TRANSLATION_Y,
            0f,
            -splashScreenView.height.toFloat()
        )
        slideUp.interpolator = AnticipateInterpolator()
        slideUp.duration = if (isCompatVersion) 1_000L else 200L
        slideUp.doOnEnd {
            splashScreenViewProvider.remove()
        }
        if (isCompatVersion) {
            // 低版本的系统，我们让图标做完动画再关闭
            waitForAnimatedIconToFinish(splashScreenViewProvider, splashScreenView, slideUp)
        } else {
            slideUp.start()
        }
    }

    private fun waitForAnimatedIconToFinish(
        splashScreenViewProvider: SplashScreenViewProvider,
        view: View,
        animator: Animator
    ) {
        val delayMillis: Long = (
                splashScreenViewProvider.iconAnimationStartMillis +
                        splashScreenViewProvider.iconAnimationDurationMillis
                ) - System.currentTimeMillis()
        view.postDelayed(delayMillis) { animator.start() }
    }
}