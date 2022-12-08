package com.pp.library_ui.widget.videoviewer

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.*
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.pp.library_ui.R

class GlobalVideoViewer : VideoViewer {

    private var exoPlayer: Player? = null
    private val playImageView: ImageView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        showLoading(false)

        autoResume = false
        playImageView = ImageView(context)
        playImageView.setImageResource(R.drawable.exo_styled_controls_play)
        playImageView.setOnClickListener {
            playImageView.visibility = View.GONE
            startGlobalPlay()
        }
        val playWidth = 36 * context.resources.displayMetrics.density
        val playHeight = 36 * context.resources.displayMetrics.density
        val layoutParams = LayoutParams(playWidth.toInt(), playHeight.toInt())
        layoutParams.gravity = Gravity.CENTER
        attachViewToParent(playImageView, childCount,
            layoutParams)
    }

    companion object {
        // 正在播放的 video
        private val sPlayingVideo = MutableLiveData<GlobalVideoViewer>()

    }

    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            if (sPlayingVideo.value == this@GlobalVideoViewer) {
                release()
            }
        }
    }

    override fun release() {
        super.release()
        exoPlayer = null
        playImageView.visibility = View.VISIBLE
        showCover(true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        lifecycleOwner?.lifecycle?.addObserver(observer)
    }

    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
        playImageView.isClickable = clickable
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleOwner?.lifecycle?.removeObserver(observer)

        val startTime = System.currentTimeMillis()
        release()
        val endTime = System.currentTimeMillis()
        Log.e("TAG", "time: ${endTime - startTime}")
    }

    private fun startGlobalPlay() {
        playUrl?.apply {

            val player = SimpleExoPlayer.Builder(context).setSkipSilenceEnabled(true).build()
            player.addListener(object : Player.EventListener {

                override fun onPlaybackStateChanged(state: Int) {
                    when (state) {
                        Player.STATE_IDLE -> {
                            playImageView.visibility = View.VISIBLE
                            showCover(true)
                        }
                    }
                }

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


            startPlay(player, this, true)

            exoPlayer = player
        }
    }

    private var playUrl: String? = ""
    fun setPlayUrlAndCover(url: String, cover: String) {
        playImageView.visibility = View.VISIBLE
        showCover(true)
        setCover(cover)

        if (url == playUrl) {
            return
        }
        this.playUrl = url
    }
}