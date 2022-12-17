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
import androidx.lifecycle.*
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.pp.library_ui.R
import com.pp.library_ui.databinding.ViewVideoviewerBinding
import com.pp.library_ui.utils.ViewTreeAppThemeViewModel
import com.pp.library_ui.utils.load
import kotlin.properties.Delegates

open class VideoViewer : FrameLayout, LifecycleEventObserver {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var controllerLoadingView: ProgressBar by Delegates.notNull()
    private var videoCoverView: ImageView by Delegates.notNull()

    private var videoViewerBinding: ViewVideoviewerBinding? = null
    var useBottomTimeBar = false
        set(value) {
            field = value
            updateTimeBar()
        }

    @SuppressLint("WrongViewCast", "Recycle")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val inflater = LayoutInflater.from(context)
        if (isInEditMode) {
            inflater.inflate(R.layout.view_videoviewer, this, true)
            return
        }

        videoViewerBinding = DataBindingUtil.inflate<ViewVideoviewerBinding>(
            inflater,
            R.layout.view_videoviewer,
            this,
            true
        )

        controllerLoadingView = findViewById<ProgressBar>(R.id.controller_loading)
        videoCoverView = findViewById<ImageView>(R.id.video_cover)

        timeBarController = findViewById<StyledPlayerControlView>(R.id.video_timebar_controller)
        timeBarController.isEnabled = false
        timeBarController.showTimeoutMs = Int.MAX_VALUE

        playerView = StyledPlayerView(context, attrs, defStyleAttr)
        playerView.setControllerVisibilityListener {
            updateTimeBar()
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VideoViewer)

        useBottomTimeBar =
            typedArray.getBoolean(R.styleable.VideoViewer_use_bottom_timeBar, useBottomTimeBar)

        typedArray.recycle()


    }

    private var timeBarController: StyledPlayerControlView by Delegates.notNull()
    private var playerView: StyledPlayerView by Delegates.notNull()
    var autoResume = true

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {
                pause()
            }
            Lifecycle.Event.ON_RESUME -> {
                if (autoResume) {
                    playerView.player?.play()
                }
            }
            Lifecycle.Event.ON_DESTROY -> {
                release()
            }
            else -> {}
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleOwner?.lifecycle?.removeObserver(this)
        lifecycleOwner = null
    }

    protected var lifecycleOwner: LifecycleOwner? = null
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            lifecycleOwner = ViewTreeLifecycleOwner.get(this)
                ?: throw RuntimeException("you should call ViewTreeLifecycleOwner.set() at first")

            val appTheme = ViewTreeAppThemeViewModel[this]
            videoViewerBinding?.themeViewModel = appTheme
            lifecycleOwner?.apply {
                videoViewerBinding?.setLifecycleOwner { this.lifecycle }
                this.lifecycle.addObserver(this@VideoViewer)
            }
        }
    }

    private var cover: String? = null
    var isShowCover = true


    fun setCover(cover: String?) {
        this.cover = cover
        videoCoverView.load(cover)
    }

    fun startPlayWhenReady(url: String?): Player {
        val player = SimpleExoPlayer.Builder(context)
            .build()
        startPlay(
            player, url, true
        )
        return player
    }

    private val playEventListener = object : Player.EventListener {

        override fun onPlayerError(error: ExoPlaybackException) {
            showLoading(false)
            updateTimeBar()
        }

        override fun onPlaybackStateChanged(state: Int) {
            when (state) {
                Player.STATE_READY -> {
                    showLoading(false)
                    showCover(false)
                    updateTimeBar()

                    if (playerView.parent != this@VideoViewer) {
                        playerView.parent.apply {
                            removeView(playerView)
                        }
                        addView(playerView, 0)
                    }
                }

                Player.STATE_IDLE -> {
                    removeView(playerView)
                    updateTimeBar()
                }
                Player.STATE_BUFFERING -> {
                }
                Player.STATE_ENDED -> {

                }
            }

        }
    }

    fun showLoading(show: Boolean) {
        controllerLoadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showCover(show: Boolean) {
        videoCoverView.visibility = if (isShowCover && show) View.VISIBLE else View.GONE
    }

    fun updateTimeBar() {
        if (!useBottomTimeBar || !isPlaying()) {
            timeBarController.visibility = View.GONE
            return
        }

        if (!playerView.useController) {
            timeBarController.visibility = VISIBLE
            return
        }

        if (playerView.isControllerFullyVisible) {
            timeBarController.visibility = GONE
            return
        }


        timeBarController.visibility = VISIBLE

    }

    fun startPlay(player: Player, url: String?, playWhenReady: Boolean = true) {
        releaseInner()
        showLoading(true)
        updateTimeBar()
        try {
            url?.apply {

                if (isShowCover && cover?.isEmpty() != false) {
                    val metadataRetriever = MediaMetadataRetriever()
                    metadataRetriever.setDataSource(this, mutableMapOf())
                    videoCoverView.setImageBitmap(metadataRetriever.frameAtTime)
                }

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(url))
                    .build()

                player.setMediaItem(mediaItem)

                player.addListener(playEventListener)

                playerView.player = player
                timeBarController.player = player
                player.playWhenReady = playWhenReady
                player.prepare()
            }
        } catch (e: java.lang.RuntimeException) {
            e.printStackTrace()
        }
    }

    private fun releaseInner() {
        playerView.player?.apply {
            release()
            removeListener(playEventListener)
        }
        playerView.player = null
        timeBarController.player = null
        removeView(playerView)
        updateTimeBar()
        showLoading(false)
    }

    open fun release() {
        releaseInner()
    }

    fun isPlaying(): Boolean {
        return playerView.player?.isPlaying == true
    }

    open fun pause() {
        playerView.player?.pause()
    }

    fun hasPlayer(): Boolean {
        return playerView.player != null
    }

    fun getPlayMediaId(): String? {
        return playerView.player?.currentMediaItem?.mediaId
    }

}
