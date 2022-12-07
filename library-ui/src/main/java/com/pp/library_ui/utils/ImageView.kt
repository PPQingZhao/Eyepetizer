package com.pp.library_ui.utils

import android.animation.Animator
import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.AnimatorRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


/**
 * 动画
 */
@SuppressLint("RestrictedApi")
fun ImageView.starAnimator(lifecycle: Lifecycle, @AnimatorRes id: Int) {
    var animator = starAnimator(id)
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            animator?.cancel()
            animator = null
        }
    })
}

/**
 * 动画
 */
@SuppressLint("RestrictedApi")
fun ImageView.starAnimator(@AnimatorRes id: Int): Animator? {
    val loadAnimator = AnimatorInflaterCompat.loadAnimator(context, id)
    loadAnimator.setTarget(this)
    loadAnimator.start()
    return loadAnimator
}

/**
 * 加载图片
 */
fun ImageView.load(path: String?) {
    val options: RequestOptions = RequestOptions()
        .skipMemoryCache(false)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    val crossFadeFactory = DrawableCrossFadeFactory.Builder()
        .setCrossFadeEnabled(true)
        .build()
    Glide.with(context)
        .load(path)
        .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
        .into(this)
}