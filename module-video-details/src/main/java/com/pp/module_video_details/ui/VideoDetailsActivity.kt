package com.pp.module_video_details.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        requireLightStatsBar(false)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        resourceId = intent?.getLongExtra("resourceId", 0)
        resourceType = intent?.getStringExtra("resourceType")

        mViewModel.getItemDetails(resourceId, resourceType)
            .observe(this, detailsObserver)
    }

    val detailsObserver = Observer { it: MetroDataBean? ->

        mBinding.video.setCover(it?.cover?.url)
        mBinding.video.startPlayWhenReady(it?.video?.playUrl)
        showDetailsFragment(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
//        Log.e("TAG", "==>resourceType: ${resourceType}")
        mViewModel.getItemDetailsWithCache(resourceId, resourceType)
            .observe(this, detailsObserver)
    }

    private fun showDetailsFragment(itemDetailsBean: MetroDataBean?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, getDetailsFragment(itemDetailsBean))
            .commitNow()
    }

    private fun getDetailsFragment(itemDetailsBean: MetroDataBean?): Fragment {
        return DetailsFragment(itemDetailsBean)
    }

    fun onBack(view: View) {
        finish()
    }

    fun onFollow(view: View) {
    }
}