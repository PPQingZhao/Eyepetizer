package com.pp.module_video_details.ui

import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.bean.ItemDetailsBean
import com.pp.library_router_service.services.RouterPath
import com.pp.module_video_details.R
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

    override fun isLightStatusBar(): Boolean {
        return false
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("TAG", "onConfigurationChanged")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
//        Log.e("TAG", "==>resourceType: ${resourceType}")

        Log.e("VideoDetailsActivity", "${mViewModel}")
        mViewModel.getItemDetails(resourceId, resourceType)
            .observe(this) {
                Log.e("VideoDetailsActivity", "${mViewModel}  details: ${it}")

                startPlay(it?.video?.playUrl)
                showDetailsFragment(it)
            }
    }

    private fun showDetailsFragment(itemDetailsBean: ItemDetailsBean?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, getDetailsFragment(itemDetailsBean))
            .commitNow()
    }

    private fun getDetailsFragment(itemDetailsBean: ItemDetailsBean?): Fragment {
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

    val videoView by lazy { VideoView(this) }

    fun onBack(view: View) {
        onBackPressed()
    }
}