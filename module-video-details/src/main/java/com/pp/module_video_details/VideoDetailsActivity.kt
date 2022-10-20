package com.pp.module_video_details

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
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

    fun onBack(view: View) {
        onBackPressed()
    }
}