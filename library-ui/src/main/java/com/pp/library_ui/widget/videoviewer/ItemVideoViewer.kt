package com.pp.library_ui.widget.videoviewer

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

class ItemVideoViewer : GlobalVideoViewer {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            scrollControllers.onEach {
                it.removeOnScrollListener(onScrollListener)
            }
        }
    }

    private val onScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    releaseBecauseInvalidityScrollControllers()
                }
            }
        }
    }

    private val scrollControllers = mutableListOf<RecyclerView>()
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleOwner?.lifecycle?.addObserver(observer)
        releaseBecauseInvalidityScrollControllers()
    }

    /**
     * 释放视频
     */
    private fun releaseBecauseInvalidityScrollControllers() {
        var parent = parent

        // 新的 scroller controllers
        val newControllers = mutableListOf<RecyclerView>()
        while (parent != null) {
            if (parent is RecyclerView) {
                if (!scrollControllers.contains(parent)) {
                    parent.addOnScrollListener(onScrollListener)
                }
                newControllers.add(parent)
            }
            parent = parent.parent
        }

        val tempControllers = scrollControllers.toMutableList()

        // 更新 scrollControllers
        scrollControllers.clear()
        scrollControllers.addAll(newControllers)

        // 清理无效的 scroll controller
        // 出现失效的controller，说明当前video脱离窗口,需要release
        val invalidityControllers = tempControllers.minus(newControllers.toSet())
        if (invalidityControllers.isNotEmpty()) {
            release()
        }
        invalidityControllers.onEach {
            it.removeOnScrollListener(onScrollListener)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleOwner?.lifecycle?.removeObserver(observer)
    }

}