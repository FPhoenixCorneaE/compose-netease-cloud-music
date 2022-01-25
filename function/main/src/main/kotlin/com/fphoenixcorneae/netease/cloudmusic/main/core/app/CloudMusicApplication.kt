package com.fphoenixcorneae.netease.cloudmusic.main.core.app

import com.facebook.drawee.backends.pipeline.Fresco
import com.fphoenixcorneae.jetpackmvvm.compose.base.application.BaseApplication

/**
 * @desc：CloudMusicApplication
 * @date：2021/11/09 10:44
 */
class CloudMusicApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}