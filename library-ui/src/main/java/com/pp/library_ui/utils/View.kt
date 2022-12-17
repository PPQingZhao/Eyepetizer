package com.pp.library_ui.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

fun View.startTranslationY(
    show: Boolean,
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    if (show) {
        translationY_show_from_bottom(updateListener, listener)
    } else {
        translationY_dismiss_to_bottom(updateListener, listener)
    }
}

fun View.translationY_show_from_bottom(
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val translationY_from_bottom = ObjectAnimator.ofFloat(this, "translationY", height * 1.0f, 0f)
    translationY_from_bottom.interpolator = LinearInterpolator()

    updateListener?.apply {
        translationY_from_bottom.addUpdateListener(this)
    }

    translationY_from_bottom.addListener(DefaultAnimatorListener(onAnimationStart = {
        translationY = height.toFloat()
        visibility = View.VISIBLE
    }))


    listener?.apply {
        translationY_from_bottom.addListener(this)
    }
    translationY_from_bottom.setTarget(this)
    translationY_from_bottom.duration = 500
    translationY_from_bottom.start()
}

fun View.translationY_dismiss_to_bottom(
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val translationY_to_bottom = ObjectAnimator.ofFloat(this, "translationY", 0f, height * 1.0f)
    translationY_to_bottom.interpolator = LinearInterpolator()

    updateListener?.apply {
        translationY_to_bottom.addUpdateListener(this)
    }

    translationY_to_bottom.addListener(DefaultAnimatorListener(onAnimationEnd = {
        visibility = View.GONE
    }))

    listener?.apply {
        translationY_to_bottom.addListener(this)
    }
    translationY_to_bottom.setTarget(this)
    translationY_to_bottom.duration = 500
    translationY_to_bottom.start()
}

fun View.startHeightAnimator(
    vararg value: Int,
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val heightAnimator = ValueAnimator.ofInt(*value)
    heightAnimator.interpolator = LinearInterpolator()
    heightAnimator.addUpdateListener {
        layoutParams.height = it.animatedValue as Int
        requestLayout()
    }
    updateListener?.apply {
        heightAnimator.addUpdateListener(this)
    }

    listener?.apply {
        heightAnimator.addListener(this)
    }
    heightAnimator.setTarget(this)
    heightAnimator.duration = 500
    heightAnimator.start()
}

fun View.startAlphaAnimator(
    vararg value: Float,
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val alphaAnimator = ObjectAnimator.ofFloat(this, "alpha", *value)

    updateListener?.apply {
        alphaAnimator.addUpdateListener(this)
    }

    listener?.apply {
        alphaAnimator.addListener(this)
    }
    alphaAnimator.duration = 1000
    alphaAnimator.start()
}