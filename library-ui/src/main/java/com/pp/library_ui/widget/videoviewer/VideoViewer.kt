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
import com.pp.library_ui.R
import com.pp.library_ui.databinding.ViewVideoviewerBinding
import com.pp.library_ui.utils.AppThemeViewModel
import com.pp.library_ui.utils.load

class VideoViewer : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val videoviewerBinding by lazy {
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

        initView()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val appTheme = AppThemeViewModel[this]
        videoviewerBinding.themeViewModel = appTheme
        ViewTreeLifecycleOwner.get(this)?.apply {
            videoviewerBinding.setLifecycleOwner { this.lifecycle }

            this.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    videoviewerBinding.playerview.player?.release()
                }

                override fun onPause(owner: LifecycleOwner) {
                    videoviewerBinding.playerview.player?.pause()
                }

                override fun onResume(owner: LifecycleOwner) {
                    videoviewerBinding.playerview.player?.play()
                }
            })
        }
    }

    private fun initView() {
    }

    private var cover: String? = null
    fun setCover(cover: String?) {
        this.cover = cover

        videoviewerBinding.videoCover.load(cover)
    }

    fun startPlayWhenReady(url: String?) {

        videoviewerBinding.controllerLoading.visibility = View.VISIBLE
        videoviewerBinding.playerview.player?.release()
        videoviewerBinding.playerview.visibility = View.GONE
        url?.apply {

            if (cover == null) {
                val metadataRetriever = MediaMetadataRetriever()
                metadataRetriever.setDataSource(this, mutableMapOf())
                videoviewerBinding.videoCover.setImageBitmap(metadataRetriever.frameAtTime)
            }

            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .build()

            val player = SimpleExoPlayer.Builder(context)
                .build()
            player.setMediaItem(mediaItem)

            player.addListener(object : Player.EventListener {

                override fun onPlaybackStateChanged(state: Int) {
                    if (Player.STATE_READY == state) {
                        videoviewerBinding.playerview.visibility = View.VISIBLE
                        videoviewerBinding.controllerLoading.visibility = View.GONE
                        videoviewerBinding.videoCover.visibility = View.GONE
                    }
                }
            })

            videoviewerBinding.playerview.player = player
            player.playWhenReady = true
            player.prepare()
        }
    }
}
