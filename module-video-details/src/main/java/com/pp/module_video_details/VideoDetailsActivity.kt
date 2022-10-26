package com.pp.module_video_details

import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.VideoView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_router_service.services.RouterPath
import com.pp.module_video_details.databinding.ActivityVideoDetailsBinding
import com.pp.mvvm.LifecycleActivity

@Route(path = RouterPath.VideoDetails.activity_video_details)
class VideoDetailsActivity :
    LifecycleActivity<ActivityVideoDetailsBinding, VideoDetailsVideoModel>() {
    override val mBinding by lazy { ActivityVideoDetailsBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<VideoDetailsVideoModel> {
        return VideoDetailsVideoModel::class.java
    }

    @JvmField
    @Autowired(name = "resourceId")
    var resourceId: Int? = 0

    @JvmField
    @Autowired(name = "resourceType")
    var resourceType: String? = ""

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("TAG", "onConfigurationChanged")
    }

    override fun customTheme(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        Log.e("TAG", "==>resourceType: ${resourceType}")

        mViewModel.getItemDetails(resourceId, resourceType)
            .observe(this) {

                startPlay(it?.video?.playUrl)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
    }

    private fun startPlay(playUrl: String?) {

        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER
        mBinding.video.addView(videoView, layoutParams)

        videoView.setVideoURI(Uri.parse(playUrl))
        videoView.start()
    }

    val videoView by lazy { VideoView(this) }

    fun onBack(view: View) {
        onBackPressed()
    }
}