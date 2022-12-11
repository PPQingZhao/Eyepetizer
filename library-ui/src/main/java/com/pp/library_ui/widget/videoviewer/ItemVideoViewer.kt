package com.pp.library_ui.widget.videoviewer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
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

    private val onAutoPlayScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    findFirstVideoAndPlay(recyclerView)
                }
            }
        }
    }

    val recyclerRect = Rect()
    val videoViewRect = Rect()
    val tmpVideoViews = mutableListOf<ItemVideoViewer>()
    private fun findFirstVideoAndPlay(recyclerView: RecyclerView) {
        tmpVideoViews.clear()
        recyclerView.getGlobalVisibleRect(recyclerRect)
        findVideoView(recyclerView)
        val videoViewer = tmpVideoViews.getOrNull(0)
        videoViewer?.startGlobalPlay()
    }

    private fun findVideoView(view: View){
        if (view is ItemVideoViewer) {
            view.getGlobalVisibleRect(videoViewRect)
            if (recyclerRect.contains(videoViewRect)) {
                tmpVideoViews.add(view)
                return
            }
            return
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findVideoView(view.getChildAt(i))
            }
        }
        return
    }

    private fun addAutoPlayListener() {
        var p = parent

        var find = false
        while (p != null) {
            if (find) {
                return
            }
            if (p is RecyclerView) {
                find = true
                p.addOnScrollListener(onAutoPlayScrollListener)
            }
            p = p.parent
        }
    }

    private fun removeAutoPlayListener() {
        var p = parent

        var find = false
        while (p != null) {
            if (find) {
                return
            }
            if (p is RecyclerView) {
                find = true
                p.removeOnScrollListener(onAutoPlayScrollListener)
            }
            p = p.parent
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
        if (autoPlay) {
            addAutoPlayListener()
        }
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
        if (autoPlay) {
            removeAutoPlayListener()
        }
    }

    private var autoPlay = false

    fun setAutoPlay(play: Boolean) {
        autoPlay = play
    }

}