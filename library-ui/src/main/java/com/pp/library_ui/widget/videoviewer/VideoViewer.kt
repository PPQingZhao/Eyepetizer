package com.pp.library_ui.widget.videoviewer

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.pp.library_ui.R
import com.pp.library_ui.databinding.ViewVideoviewerBinding
import com.pp.library_ui.utils.AppThemeViewModel
import com.pp.library_ui.utils.load

open class VideoViewer : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    protected val videoviewerBinding by lazy {
        DataBindingUtil.inflate<ViewVideoviewerBinding>(
            LayoutInflater.from(context),
            R.layout.view_videoviewer,
            this@VideoViewer,
            true
        )
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        playerView =
            LayoutInflater.from(context)
                .inflate(R.layout.video_styled_player_view, this, false) as StyledPlayerView
    }

    private var playerView: StyledPlayerView? = null
    var autoResume = true
    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            playerView?.player?.release()
        }

        override fun onPause(owner: LifecycleOwner) {
            playerView?.player?.pause()
        }

        override fun onStop(owner: LifecycleOwner) {
            playerView?.player?.stop()
        }

        override fun onResume(owner: LifecycleOwner) {
            if (autoResume) {
                playerView?.player?.play()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ViewTreeLifecycleOwner.get(this)?.lifecycle?.removeObserver(observer)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val lifecycleOwner = ViewTreeLifecycleOwner.get(this)
            ?: throw RuntimeException("you should call ViewTreeLifecycleOwner.set() at first")

        val appTheme = AppThemeViewModel[this]
        videoviewerBinding.themeViewModel = appTheme
        lifecycleOwner.apply {
            videoviewerBinding.setLifecycleOwner { this.lifecycle }

            this.lifecycle.addObserver(observer)
        }
    }

    private var cover: String? = null
    fun setCover(cover: String?) {
        this.cover = cover
        videoviewerBinding.videoCover.load(cover)
    }

    fun startPlayWhenReady(url: String?) {
        startPlay(
            SimpleExoPlayer.Builder(context)
                .build(), url, true
        )
    }

    fun startPlay(player: Player, url: String?, playWhenReady: Boolean = true) {

        videoviewerBinding.controllerLoading.visibility = View.VISIBLE
        playerView?.player?.release()
        try {
            url?.apply {

                if (cover == null) {
                    val metadataRetriever = MediaMetadataRetriever()
                    metadataRetriever.setDataSource(this, mutableMapOf())
                    videoviewerBinding.videoCover.setImageBitmap(metadataRetriever.frameAtTime)
                }

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(url))
                    .build()

                player.setMediaItem(mediaItem)

                player.addListener(object : Player.EventListener {

                    override fun onPlaybackStateChanged(state: Int) {
                        when (state) {
                            Player.STATE_READY -> {
                                videoviewerBinding.controllerLoading.visibility = GONE
                                videoviewerBinding.videoCover.visibility = GONE
                                if (playerView?.parent != this@VideoViewer) {
                                    playerView?.parent.apply {
                                        removeView(playerView)
                                    }
                                    addView(playerView, 0)
                                }
                            }

                            Player.STATE_IDLE -> {
                                videoviewerBinding.videoCover.visibility = VISIBLE
                                removeView(playerView)
                            }
                            Player.STATE_BUFFERING -> {
                            }
                            Player.STATE_ENDED -> {
                            }
                        }

                    }
                })

                playerView?.player = player
                player.playWhenReady = playWhenReady
                player.prepare()
            }
        } catch (e: java.lang.RuntimeException) {
            e.printStackTrace()
        }
    }

    fun release() {
        playerView?.player?.apply {
            release()
        }
    }

    fun isPlaying(): Boolean {
        return playerView?.player?.isPlaying == true
    }

    fun pause() {
        playerView?.player?.pause()
    }
}
