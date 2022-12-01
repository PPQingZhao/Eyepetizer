package com.pp.library_ui.utils

import android.view.animation.Animation

class DefaultAnimationListener(
    private val onAnimationStart: ((animation: Animation?) -> Unit)? = null,
    private val onAnimationEnd: ((animation: Animation?) -> Unit)? = null,
    private val onAnimationRepeat: ((animation: Animation?) -> Unit)? = null
) :
    Animation.AnimationListener {
    override fun onAnimationStart(animation: Animation?) {
        onAnimationStart?.invoke(animation)
    }

    override fun onAnimationEnd(animation: Animation?) {
        onAnimationEnd?.invoke(animation)
    }

    override fun onAnimationRepeat(animation: Animation?) {
        onAnimationRepeat?.invoke(animation)
    }
}