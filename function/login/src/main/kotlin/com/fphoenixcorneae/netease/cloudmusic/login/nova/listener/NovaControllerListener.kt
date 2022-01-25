package com.fphoenixcorneae.netease.cloudmusic.login.nova.listener

import android.graphics.Bitmap
import android.graphics.drawable.Animatable
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import com.facebook.imagepipeline.core.ExecutorSupplier
import com.facebook.imagepipeline.image.CloseableBitmap
import com.facebook.imagepipeline.image.ImageInfo

/**
 * @desc：NovaControllerListener
 * @date：2021/11/15 11:42
 */
open class NovaControllerListener : BaseControllerListener<ImageInfo?>() {

    override fun onFailure(str: String, th: Throwable) {}

    override fun onIntermediateImageFailed(str: String, th: Throwable) {}

    override fun onRelease(str: String) {}

    override fun onSubmit(str: String, obj: Any?) {}

    override fun onFinalImageSet(str: String, imageInfo: ImageInfo?, animatable: Animatable?) {
        setBitmap(imageInfo, true)
    }

    override fun onIntermediateImageSet(str: String, imageInfo: ImageInfo?) {
        setBitmap(imageInfo, false)
    }

    open fun onUpdateProgress(i: Int) {}

    fun onBitmapSet(
        bitmap: Bitmap?,
        platformBitmapFactory: PlatformBitmapFactory?,
        executorSupplier: ExecutorSupplier?
    ) {
    }

    open fun onFinalBitmapSet(
        bitmap: Bitmap?,
        platformBitmapFactory: PlatformBitmapFactory?,
        executorSupplier: ExecutorSupplier?
    ) {
    }

    fun onIntermediateBitmapSet(
        bitmap: Bitmap?,
        platformBitmapFactory: PlatformBitmapFactory?,
        executorSupplier: ExecutorSupplier?
    ) {
    }

    private fun setBitmap(imageInfo: ImageInfo?, z: Boolean) {
        if (imageInfo is CloseableBitmap) {
            val underlyingBitmap = imageInfo.underlyingBitmap
            val imagePipelineFactory = Fresco.getImagePipelineFactory()
            val platformBitmapFactory = imagePipelineFactory.platformBitmapFactory
            val executorSupplier = imagePipelineFactory.imagePipeline.config.executorSupplier
            onBitmapSet(underlyingBitmap, platformBitmapFactory, executorSupplier)
            if (z) {
                onFinalBitmapSet(underlyingBitmap, platformBitmapFactory, executorSupplier)
            } else {
                onIntermediateBitmapSet(underlyingBitmap, platformBitmapFactory, executorSupplier)
            }
        }
    }
}