package com.app.libraries.volley.imageutils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Created by chensuilun on 2017/3/22.
 */

object BitmapUtils {
    private var sDesiredWidth: Int = 0

    private var sDesiredHeight: Int = 0

    fun decodeSampledBitmapFromResource(
        res: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)
        options = getBestOptions(options, reqWidth, reqHeight)
        val src = BitmapFactory.decodeResource(res, resId, options)
        return createScaleBitmap(src, sDesiredWidth, sDesiredHeight)
    }

    private fun createScaleBitmap(
        tempBitmap: Bitmap?,
        desiredWidth: Int,
        desiredHeight: Int
    ): Bitmap? {
        // If necessary, scale down to the maximal acceptable size.
        if (tempBitmap != null && (tempBitmap.width > desiredWidth || tempBitmap.height > desiredHeight)) {
            val bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight, true)
            tempBitmap.recycle()
            return bitmap
        } else {
            return tempBitmap
        }
    }

    private fun getBestOptions(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): BitmapFactory.Options {
        val actualWidth = options.outWidth
        val actualHeight = options.outHeight
        // Then compute the dimensions we would ideally like to decode to.
        sDesiredWidth = getResizedDimension(reqWidth, reqHeight, actualWidth, actualHeight)
        sDesiredHeight = getResizedDimension(reqHeight, reqWidth, actualHeight, actualWidth)
        // 根据现在得到计算inSampleSize
        options.inSampleSize =
            calculateBestInSampleSize(actualWidth, actualHeight, sDesiredWidth, sDesiredHeight)
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false
        return options
    }

    /**
     * Returns the largest power-of-two divisor for use in downscaling a bitmap
     * that will not result in the scaling past the desired dimensions.
     *
     * @param actualWidth   Actual width of the bitmap
     * @param actualHeight  Actual height of the bitmap
     * @param desiredWidth  Desired width of the bitmap
     * @param desiredHeight Desired height of the bitmap
     */
    // Visible for testing.
    private fun calculateBestInSampleSize(
        actualWidth: Int,
        actualHeight: Int,
        desiredWidth: Int,
        desiredHeight: Int
    ): Int {
        val wr = actualWidth.toDouble() / desiredWidth
        val hr = actualHeight.toDouble() / desiredHeight
        val ratio = Math.min(wr, hr)
        var inSampleSize = 1.0f
        while (inSampleSize * 2 <= ratio) {
            inSampleSize *= 2f
        }

        return inSampleSize.toInt()
    }

    /**
     * Scales one side of a rectangle to fit aspect ratio.
     *
     * @param maxPrimary      Maximum size of the primary dimension (i.e. width for max
     * width), or zero to maintain aspect ratio with secondary
     * dimension
     * @param maxSecondary    Maximum size of the secondary dimension, or zero to maintain
     * aspect ratio with primary dimension
     * @param actualPrimary   Actual size of the primary dimension
     * @param actualSecondary Actual size of the secondary dimension
     */
    private fun getResizedDimension(
        maxPrimary: Int,
        maxSecondary: Int,
        actualPrimary: Int,
        actualSecondary: Int
    ): Int {
        val ratio = actualSecondary.toDouble() / actualPrimary.toDouble()
        var resized = maxPrimary
        if (resized * ratio > maxSecondary) {
            resized = (maxSecondary / ratio).toInt()
        }
        return resized
    }
}

