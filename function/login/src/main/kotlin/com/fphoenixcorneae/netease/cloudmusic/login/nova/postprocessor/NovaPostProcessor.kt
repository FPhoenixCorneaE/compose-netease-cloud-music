package com.fphoenixcorneae.netease.cloudmusic.login.nova.postprocessor

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor

/**
 * @desc：NovaPostProcessor
 * @date：2021/11/15 15:13
 */
class NovaPostProcessor(blurRadius: Int) : IterativeBoxBlurPostProcessor(blurRadius) {

    override fun process(bitmap: Bitmap, platformBitmapFactory: PlatformBitmapFactory): CloseableReference<Bitmap>? {
        val createBitmapInternal =
            platformBitmapFactory.createBitmapInternal(bitmap.width, bitmap.height, FALLBACK_BITMAP_CONFIGURATION)
        return try {
            process(createBitmapInternal.get(), bitmap)
            CloseableReference.cloneOrNull(createBitmapInternal)
        } finally {
            CloseableReference.closeSafely(createBitmapInternal)
        }
    }
}