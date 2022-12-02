package com.pp.library_ui.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.pp.library_ui.R

/**
 * 动画 repeat loading
 */
@SuppressLint("RestrictedApi")
fun ImageView.startRepeatLoading() {
    val loadAnimator =
        AnimatorInflaterCompat.loadAnimator(context, R.animator.animator_repeat_loading)
    loadAnimator.setTarget(this)
    loadAnimator.start()
}

/**
 * 动画 loading1
 */
@SuppressLint("RestrictedApi")
fun ImageView.startLoading1() {
    val loadAnimator1 = AnimatorInflaterCompat.loadAnimator(context, R.animator.animator_loading1)
    loadAnimator1.setTarget(this)
    loadAnimator1.addListener(DefaultAnimatorListener(onAnimationEnd = {
        startRepeatLoading()
    }))
    loadAnimator1.start()
}

/**
 * 动画 loading2
 */
@SuppressLint("RestrictedApi")
fun ImageView.startLoading2() {
    val loadAnimator2 = AnimatorInflaterCompat.loadAnimator(context, R.animator.animator_loading2)
    loadAnimator2.setTarget(this)
    loadAnimator2.addListener(DefaultAnimatorListener(onAnimationEnd = {
        startRepeatLoading()
    }))
    loadAnimator2.start()
}

/**
 * 动画 loading3
 */
@SuppressLint("RestrictedApi")
fun ImageView.startLoading3() {
    val loadAnimator3 = AnimatorInflaterCompat.loadAnimator(context, R.animator.animator_loading3)
    loadAnimator3.setTarget(this)
    loadAnimator3.addListener(DefaultAnimatorListener(onAnimationEnd = {
        startRepeatLoading()
    }))
    loadAnimator3.start()
}

/**
 * 动画 loading4
 */
@SuppressLint("RestrictedApi")
fun ImageView.startLoading4() {
    val loadAnimator = AnimatorInflaterCompat.loadAnimator(context, R.animator.animator_loading4)
    loadAnimator.setTarget(this)
    loadAnimator.addListener(DefaultAnimatorListener(onAnimationEnd = {
        startRepeatLoading()
    }))
    loadAnimator.start()
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