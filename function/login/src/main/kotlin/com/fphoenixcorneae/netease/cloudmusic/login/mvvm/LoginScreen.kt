package com.fphoenixcorneae.netease.cloudmusic.login.mvvm

import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isGone
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.fresco.animation.drawable.AnimatedDrawable2
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.fphoenixcorneae.netease.cloudmusic.login.R
import com.fphoenixcorneae.netease.cloudmusic.login.nova.listener.SafeNovaControllerListener

/**
 * @desc：LoginScreen
 * @date：2021/11/15 16:19
 */
@Composable
fun LoginScreen(context: Context = LocalContext.current) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_0xdb2c1f))
    ) {
        val simpleDraweeView = SimpleDraweeView(context).apply {
            val maxBitmapSize = 2048f
            val width = LocalDensity.current.run { 360.dp.roundToPx() }
            val height = LocalDensity.current.run { 360.dp.roundToPx() }
            layoutParams = ViewGroup.LayoutParams(width, height)
            val hierarchy = GenericDraweeHierarchyBuilder(context.resources).run {
                actualImageScaleType = ScalingUtils.ScaleType.CENTER_INSIDE
                fadeDuration = 0
                setPlaceholderImage(R.mipmap.ic_splash_slogan)
                setBackgroundColor(colorResource(id = R.color.color_0xdb2c1f).toArgb())
                build()
            }
            setHierarchy(hierarchy)
            val imgUri = Uri.parse("res:///${R.mipmap.ic_splash_slogan}")
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(imgUri).run {
                val resizeOptions = ResizeOptions(width, height, maxBitmapSize)
                setResizeOptions(resizeOptions)
                rotationOptions = RotationOptions.autoRotate()
                if (!UriUtil.isNetworkUri(imgUri)) {
                    isLocalThumbnailPreviewsEnabled = true
                }
                val imageDecodeOptions = ImageDecodeOptionsBuilder<ImageDecodeOptionsBuilder<*>>().run {
                    build()
                }
                setImageDecodeOptions(imageDecodeOptions)
                build()
            }
            controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(imageRequest)
                .setImageRequest(imageRequest)
                .setControllerListener(object : SafeNovaControllerListener(context) {
                    override fun onSafeFinalImageSet(str: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                        if (animatable is AnimatedDrawable2) {
                            animatable.start()
                            val frameCount = animatable.frameCount
                            animatable.setDrawListener { animatedDrawable, frameScheduler, frameNumberToDraw, frameDrawn, isAnimationRunning, animationStartTimeMs, animationTimeMs, lastFrameAnimationTimeMs, actualRenderTimeStartMs, actualRenderTimeEndMs, startRenderTimeForNextFrameMs, scheduledRenderTimeForNextFrameMs ->
                                if (frameNumberToDraw >= frameCount - 1 || startRenderTimeForNextFrameMs == -1L) {
                                    isGone = true
                                }
                            }
                        }
                    }
                })
                .build()
        }

        AndroidView(
            factory = {
                simpleDraweeView
            },
            modifier = Modifier
                .padding(top = 45.dp)
                .align(alignment = Alignment.TopCenter)
        ) {

        }
    }
}