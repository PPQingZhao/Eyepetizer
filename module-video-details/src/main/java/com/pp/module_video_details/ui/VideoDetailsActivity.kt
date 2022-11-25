package com.pp.module_video_details.ui

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_base.base.ThemeActivity
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_router_service.services.RouterPath
import com.pp.module_video_details.R
import com.pp.module_video_details.databinding.ActivityVideoDetailsBinding

@Route(path = RouterPath.VideoDetails.activity_video_details)
class VideoDetailsActivity :
    ThemeActivity<ActivityVideoDetailsBinding, VideoDetailsVideoModel>() {
    override val mBinding by lazy { ActivityVideoDetailsBinding.inflate(layoutInflater) }

    override fun getModelClazz(): Class<VideoDetailsVideoModel> {
        return VideoDetailsVideoModel::class.java
    }

    @JvmField
    @Autowired(name = "resourceId")
    var resourceId: Long? = 0

    @JvmField
    @Autowired(name = "resourceType")
    var resourceType: String? = ""

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("TAG", "onConfigurationChanged")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireLightStatsBar(false)
        ARouter.getInstance().inject(this)
//        Log.e("TAG", "==>resourceType: ${resourceType}")

        mViewModel.getItemDetails(resourceId, resourceType)
            .observe(this) {

                startPlay(it?.video?.playUrl)
                showDetailsFragment(it)
            }
    }

    private fun showDetailsFragment(itemDetailsBean: MetroDataBean?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, getDetailsFragment(itemDetailsBean))
            .commitNow()
    }

    private fun getDetailsFragment(itemDetailsBean: MetroDataBean?): Fragment {
        return DetailsFragment(itemDetailsBean)
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
    }

    private fun startPlay(playUrl: String?) {

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.gravity = Gravity.CENTER
        mBinding.video.addView(videoView, layoutParams)

        videoView.setVideoURI(Uri.parse(playUrl))
        videoView.start()
    }

    private val videoView by lazy { VideoView(this) }

    fun onBack(view: View) {
        onBackPressed()
    }
}