package com.fphoenixcorneae.netease.cloudmusic.login.nova.listener

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Animatable
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import com.facebook.imagepipeline.core.ExecutorSupplier
import com.facebook.imagepipeline.image.CloseableStaticBitmap
import com.facebook.imagepipeline.image.ImageInfo
import java.lang.ref.WeakReference

/**
 * @desc：SafeNovaControllerListener
 * @date：2021/11/16 10:08
 */
open class SafeNovaControllerListener(obj: Any) : NovaControllerListener() {
    private val mHostRef: WeakReference<Any> = WeakReference(obj)

    open fun onSafeFailure(str: String?, th: Throwable?) {}

    open fun onSafeFinalBitmapSet(
        bitmap: Bitmap?,
        platformBitmapFactory: PlatformBitmapFactory?,
        executorSupplier: ExecutorSupplier?
    ) {
    }

    open fun onSafeIntermediateImageFailed(str: String?, th: Throwable?) {}

    open fun onSafeIntermediateImageSet(str: String?, imageInfo: ImageInfo?) {}

    open fun onSafeRelease(str: String?) {}

    open fun onSafeSubmit(str: String?, obj: Any?) {}

    open fun onSafeUpdateProgress(i: Int) {}

    override fun onSubmit(str: String, obj: Any?) {
        if (safe()) {
            onSafeSubmit(str, obj)
        }
    }

    override fun onFinalImageSet(str: String, imageInfo: ImageInfo?, animatable: Animatable?) {
        if (safe()) {
            onSafeFinalImageSet(str, imageInfo, animatable)
        }
    }

    override fun onIntermediateImageSet(str: String, imageInfo: ImageInfo?) {
        if (safe()) {
            onSafeIntermediateImageSet(str, imageInfo)
        }
    }

    override fun onIntermediateImageFailed(str: String, th: Throwable) {
        if (safe()) {
            onSafeIntermediateImageFailed(str, th)
        }
    }

    override fun onFailure(str: String, th: Throwable) {
        if (safe()) {
            onSafeFailure(str, th)
        }
    }

    override fun onRelease(str: String) {
        if (safe()) {
            onSafeRelease(str)
        }
    }

    override fun onFinalBitmapSet(
        bitmap: Bitmap?,
        platformBitmapFactory: PlatformBitmapFactory?,
        executorSupplier: ExecutorSupplier?
    ) {
        if (safe()) {
            onSafeFinalBitmapSet(bitmap, platformBitmapFactory, executorSupplier)
        }
    }

    override fun onUpdateProgress(i: Int) {
        if (safe()) {
            onSafeUpdateProgress(i)
        }
    }

    open fun onSafeFinalImageSet(str: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
        if (imageInfo is CloseableStaticBitmap) {
            val imagePipelineFactory = Fresco.getImagePipelineFactory()
            onSafeFinalBitmapSet(
                imageInfo.underlyingBitmap,
                imagePipelineFactory.platformBitmapFactory,
                imagePipelineFactory.imagePipeline.config.executorSupplier
            )
        }
    }

    private fun safe(): Boolean {
        val obj = mHostRef.get() ?: return false
        if (obj is Activity) {
            return !obj.isFinishing
        }
        if (obj !is Fragment) {
            return true
        }
        val activity = obj.activity
        return activity != null && !activity.isFinishing && obj.isAdded
    }

}