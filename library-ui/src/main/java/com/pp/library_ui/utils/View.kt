package com.pp.library_ui.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View

fun View.translationY(
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
    val tanslationY_from_bottom = ValueAnimator.ofFloat(1.0f, 0f)
    tanslationY_from_bottom.addUpdateListener {
        if (height > 0) {
            translationY = height * (it.animatedValue as Float)
        }
    }

    updateListener?.apply {
        tanslationY_from_bottom.addUpdateListener(this)
    }

    tanslationY_from_bottom.addListener(DefaultAnimatorListener(onAnimationStart = {
        translationY = height.toFloat()
        visibility = View.VISIBLE
    }))


    listener?.apply {
        tanslationY_from_bottom.addListener(this)
    }
    tanslationY_from_bottom.setTarget(this)
    tanslationY_from_bottom.duration = 500
    tanslationY_from_bottom.start()
}

fun View.translationY_dismiss_to_bottom(
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val tanslationY_to_bottom = ValueAnimator.ofFloat(0f, 1.0f)
    tanslationY_to_bottom.addUpdateListener {
        translationY = height * (it.animatedValue as Float)
    }
    updateListener?.apply {
        tanslationY_to_bottom.addUpdateListener(this)
    }

    tanslationY_to_bottom.addListener(DefaultAnimatorListener(onAnimationEnd = {
        visibility = View.GONE
    }))

    listener?.apply {
        tanslationY_to_bottom.addListener(this)
    }
    tanslationY_to_bottom.setTarget(this)
    tanslationY_to_bottom.duration = 500
    tanslationY_to_bottom.start()
}

fun View.heightAnimator(
    vararg value: Int,
    updateListener: ValueAnimator.AnimatorUpdateListener? = null,
    listener: Animator.AnimatorListener? = null,
) {
    val heightAnimator = ValueAnimator.ofInt(*value)
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

fun View.alpha(
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