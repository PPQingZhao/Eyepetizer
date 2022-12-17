package com.pp.library_ui.widget.videoviewer

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

/**
 * 用于recyclerview item播放视频
 * ①继承自GlobalVideoViewer,实现同一时间只有一个item 播放视频
 * ②item滚动结束,如果item脱离窗口则自动停止播放 OnVideoScrollListener
 * ③item滚动结束,当 autoPlay = true时,会自动播放屏幕中第一条视频
 */
class ItemVideoViewer : GlobalVideoViewer {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_PAUSE) {
            release()
            return
        }
        super.onStateChanged(source, event)
    }

    /**
     * video 脱离窗口需要 release
     */
    private fun releaseIfDetachedFromWindow(listener: OnVideoScrollListener) {

        if (!hasPlayer()) {
            return
        }

        // 已脱离窗口
        if (windowId == null) {
            release()
            return
        }

        /**
        未脱离窗口:
        ① item 滑出去后又滑回来 ==> 不需要release
        ② item 被复用了 ==> 如果playUrl不一致且正在播放新的url,不需要release
         */
        if (listener.playUrl?.isEmpty() != false) {
            return
        }

        // item 复用情况
        if (listener.playUrl != playUrl) {
            if (listener.playUrl == getPlayMediaId()) {
                release()
            }
            return
        }

        // item 滑出去后又滑回来
        if (listener.mediaId == getPlayMediaId()) {
            return
        }

        release()
    }

    private fun autoPlay(recyclerView: RecyclerView) {
        if (!autoPlay) {
            return
        }
        findFirstVideoAndPlay(recyclerView)
    }

    private val recyclerRect = Rect()
    private val videoViewRect = Rect()
    private val tmpVideoViews = mutableListOf<ItemVideoViewer>()
    private fun findFirstVideoAndPlay(recyclerView: RecyclerView) {
        tmpVideoViews.clear()
        recyclerView.getGlobalVisibleRect(recyclerRect)
        findVideoView(recyclerView)
        val videoViewer = tmpVideoViews.getOrNull(0)
        videoViewer?.startAutoPlay()
    }

    private fun startAutoPlay() {
        if (isPlaying()) {
            return
        }
        startGlobalPlay()
    }

    private fun findVideoView(view: View) {
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

    private fun addOnVideoScrollListeners() {
        var p = parent

        while (p != null) {
            if (p is RecyclerView) {
                val onVideoScrollListener = OnVideoScrollListener(lifecycleOwner!!, this, p)
                p.addOnScrollListener(onVideoScrollListener)
            }
            p = p.parent
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addOnVideoScrollListeners()
//        Log.v("GlobalVideoViewer", "attached url: ${playUrl}")
    }

    private var autoPlay = false

    fun setAutoPlay(play: Boolean) {
        autoPlay = play
    }

    private class OnVideoScrollListener(
        val lifecycleOwner: LifecycleOwner,
        val video: ItemVideoViewer,
        val recyclerView: RecyclerView,
    ) : OnScrollListener(), OnAttachStateChangeListener {

        private val observer = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                recyclerView.removeOnScrollListener(this@OnVideoScrollListener)
            }
        }

        val mediaId: String?
        val playUrl: String?

        init {
            lifecycleOwner.lifecycle.addObserver(observer)
            video.addOnAttachStateChangeListener(this)
            playUrl = video.playUrl
            mediaId = video.playUrl
        }

        /**
         * 记录当前 OnAttachStateChangeListener 状态
         * isDetached: true 表示当前 OnAttachStateChangeListener 失效
         */
        private var isDetached = false
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            Log.v("GlobalVideoViewer", "newState: ${newState} : ${playUrl}")
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    if (isDetached) {
//                        Log.v("GlobalVideoViewer", "detach release: ${playUrl}")
                        releaseOnDetachedAndIdle(recyclerView)

                    } else {
                        video.autoPlay(recyclerView)
                    }

                }
            }
        }

        private fun releaseOnDetachedAndIdle(recyclerView: RecyclerView) {
            recyclerView.removeOnScrollListener(this)
            video.releaseIfDetachedFromWindow(this@OnVideoScrollListener)
        }

        override fun onViewAttachedToWindow(v: View?) {
        }

        override fun onViewDetachedFromWindow(v: View?) {
//            Log.e("GlobalVideoViewer", "detach: $playUrl")
            lifecycleOwner.lifecycle.removeObserver(observer)
            video.removeOnAttachStateChangeListener(this)
            isDetached = true
            if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this)
            }
        }

    }

}