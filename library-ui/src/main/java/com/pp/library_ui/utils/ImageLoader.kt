package com.pp.library_ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

object ImageLoader {


    fun load(imageView: ImageView, path: String?) {
        val options: RequestOptions = RequestOptions()
            .skipMemoryCache(false)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        val crossFadeFactory = DrawableCrossFadeFactory.Builder()
            .setCrossFadeEnabled(true)
            .build()
        Glide.with(imageView)
            .load(path)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
            .into(imageView)
    }
}