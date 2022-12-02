package com.pp.library_ui.utils

import android.animation.Animator

class DefaultAnimatorListener(
    private val onAnimationStart: ((animator: Animator?) -> Unit)? = null,
    private val onAnimationEnd: ((animator: Animator?) -> Unit)? = null,
    private val onAnimationCancel: ((animator: Animator?) -> Unit)? = null,
    private val onAnimationRepeat: ((animator: Animator?) -> Unit)? = null,
) :
    Animator.AnimatorListener {
    override fun onAnimationStart(animator: Animator?) {
        onAnimationStart?.invoke(animator)
    }

    override fun onAnimationEnd(animator: Animator?) {
        onAnimationEnd?.invoke(animator)
    }

    override fun onAnimationCancel(animator: Animator?) {
        onAnimationCancel?.invoke(animator)
    }

    override fun onAnimationRepeat(animator: Animator?) {
        onAnimationRepeat?.invoke(animator)
    }

}