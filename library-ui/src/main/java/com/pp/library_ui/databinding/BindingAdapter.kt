package com.pp.library_ui.databinding

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.pp.library_ui.databinding.utils.ImageLoader

object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:src")
    fun setImageResource(iv: ImageView, @DrawableRes res: Int) {
        Log.e("TAG","res: $res")
        iv.setImageResource(res)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("android:bindSrc")
    fun setImageResource(imageView: ImageView, path: String?) {
        Log.e("TAG","path: $path")
        ImageLoader.load(imageView, path)
    }
}