package com.fphoenixcorneae.netease.cloudmusic.login.mvvm

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.jetpackmvvm.compose.base.activity.BaseActivity
import com.fphoenixcorneae.netease.cloudmusic.common.constant.DRouterConst

/**
 * @desc：LoginActivity
 * @date：2021/11/12 14:59
 */
@Router(path = DRouterConst.Login)
class LoginActivity : BaseActivity() {

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
        setRealContent {
            LoginScreen()
        }
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun toolbarVisible(): Boolean {
        return false
    }
}