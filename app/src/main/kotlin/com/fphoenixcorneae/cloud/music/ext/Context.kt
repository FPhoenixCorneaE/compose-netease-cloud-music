package com.fphoenixcorneae.cloud.music.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 打开浏览器
 */
fun Context.openBrowser(url: String) {
    runCatching {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }.onFailure {
        it.printStackTrace()
    }
}