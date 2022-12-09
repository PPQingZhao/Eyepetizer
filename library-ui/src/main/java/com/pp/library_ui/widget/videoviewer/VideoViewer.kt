package com.pp.library_ui.widget.videoviewer

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.google.android.exoplayer2.ExoPlaybackException
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

    private var controllerLoadingView: ProgressBar? = null
    private var videoCoverView: ImageView? = null

    private var videoViewerBinding: ViewVideoviewerBinding? = null

    @SuppressLint("WrongViewCast")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val inflater = LayoutInflater.from(context)
        if (isInEditMode) {
            inflater.inflate(R.layout.view_videoviewer, this, true)

        } else {
            videoViewerBinding = DataBindingUtil.inflate<ViewVideoviewerBinding>(inflater,
                R.layout.view_videoviewer,
                this,
                true)

            playerView = inflater
                .inflate(R.layout.video_styled_player_view, this, false) as StyledPlayerView
        }

        controllerLoadingView = findViewById<ProgressBar>(R.id.controller_loading)
        videoCoverView = findViewById<ImageView>(R.id.video_cover)


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
//            playerView?.player?.stop()
        }

        override fun onResume(owner: LifecycleOwner) {
            if (autoResume) {
                playerView?.player?.play()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleOwner?.lifecycle?.removeObserver(observer)
        lifecycleOwner = null
    }

    protected var lifecycleOwner: LifecycleOwner? = null
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            lifecycleOwner = ViewTreeLifecycleOwner.get(this)
                ?: throw RuntimeException("you should call ViewTreeLifecycleOwner.set() at first")

            val appTheme = AppThemeViewModel[this]
            videoViewerBinding?.themeViewModel = appTheme
            lifecycleOwner?.apply {
                videoViewerBinding?.setLifecycleOwner { this.lifecycle }
                this.lifecycle.addObserver(observer)
            }
        }
    }

    private var cover: String? = null
    fun setCover(cover: String?) {
        this.cover = cover
        videoCoverView?.load(cover)
    }

    fun startPlayWhenReady(url: String?) {
        startPlay(
            SimpleExoPlayer.Builder(context)
                .build(), url, true
        )
    }

    private val playEventListener = object : Player.EventListener {

        override fun onPlayerError(error: ExoPlaybackException) {
            showLoading(false)
        }

        override fun onPlaybackStateChanged(state: Int) {
            when (state) {
                Player.STATE_READY -> {
                    showLoading(false)
                    showCover(false)
                    if (playerView?.parent != this@VideoViewer) {
                        playerView?.parent.apply {
                            removeView(playerView)
                        }
                        addView(playerView, 0)
                    }
                }

                Player.STATE_IDLE -> {
                    removeView(playerView)
                }
                Player.STATE_BUFFERING -> {
                }
                Player.STATE_ENDED -> {

                }
            }

        }
    }

    fun showLoading(show: Boolean) {
        controllerLoadingView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showCover(show: Boolean) {
        videoCoverView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun startPlay(player: Player, url: String?, playWhenReady: Boolean = true) {
        showLoading(true)
        internalRelease()
        try {
            url?.apply {

                if (cover?.isEmpty() != false) {
                    val metadataRetriever = MediaMetadataRetriever()
                    metadataRetriever.setDataSource(this, mutableMapOf())
                    videoCoverView?.setImageBitmap(metadataRetriever.frameAtTime)
                }

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(url))
                    .build()

                player.setMediaItem(mediaItem)

                player.addListener(playEventListener)

                playerView?.player = player
                player.playWhenReady = playWhenReady
                player.prepare()
            }
        } catch (e: java.lang.RuntimeException) {
            e.printStackTrace()
        }
    }

    private fun internalRelease() {
        playerView?.player?.apply {
            release()
            removeListener(playEventListener)
            removeView(playerView)
        }
    }

    open fun release() {
        internalRelease()
    }

    fun isPlaying(): Boolean {
        return playerView?.player?.isPlaying == true
    }

    fun pause() {
        playerView?.player?.pause()
    }
}
