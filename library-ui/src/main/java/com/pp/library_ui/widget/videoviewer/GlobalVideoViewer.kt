package com.pp.library_ui.widget.videoviewer

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.*
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.pp.library_ui.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GlobalVideoViewer : VideoViewer {

    private val exoPlayer: Player

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        videoviewerBinding.controllerLoading.visibility = View.GONE

        exoPlayer = SimpleExoPlayer.Builder(context).build()
        exoPlayer.addListener(object : Player.EventListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                // 当前 video 正在播放, sPlayingVideo 记录
                if (isPlaying) {
                    if (sPlayingVideo.value != this@GlobalVideoViewer && sPlayingVideo.value?.isPlaying() == true) {
                        sPlayingVideo.value?.pause()
                    }

                    sPlayingVideo.value = this@GlobalVideoViewer
                } else {
                    // 当前 video 停止播放,sPlayingVideo清除当前video
                    if (sPlayingVideo.value == this@GlobalVideoViewer) {
                        sPlayingVideo.value = null
                    }
                }
            }

        })

        autoResume = false

        val iv_play = ImageView(context)
        iv_play.setImageResource(R.drawable.exo_styled_controls_play)
        iv_play.scaleType = ImageView.ScaleType.CENTER_CROP
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER

        attachViewToParent(iv_play, childCount, layoutParams)
        iv_play.setOnClickListener {
            removeView(iv_play)
            startGlobalPlay()
        }
    }

    companion object {
        // 正在播放的 video
        private val sPlayingVideo = MutableLiveData<GlobalVideoViewer>()

    }

    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            if (sPlayingVideo.value == this@GlobalVideoViewer) {
                sPlayingVideo.value?.release()
                sPlayingVideo.value = null
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        ViewTreeLifecycleOwner.get(this)!!.lifecycle.addObserver(observer)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        val lifecycleOwner = ViewTreeLifecycleOwner.get(this)
        lifecycleOwner?.lifecycle?.removeObserver(observer)
        if (exoPlayer.isPlaying) {
            lifecycleOwner?.lifecycleScope?.launch {
                flow<Player> {
                    emit(exoPlayer)
                }.collect {
                    it.stop()
                }
            } ?: exoPlayer.stop()
        }
    }

    private fun startGlobalPlay() {
        playUrl?.apply {
            startPlay(exoPlayer, this, true)
        }
    }

    private var playUrl: String? = ""
    fun setPlayUrl(url: String) {
        this.playUrl = url
    }
}