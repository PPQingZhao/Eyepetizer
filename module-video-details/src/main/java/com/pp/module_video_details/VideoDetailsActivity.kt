package com.pp.module_video_details

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_network.eyepetizer.ApiService
import com.pp.library_router_service.services.RouterPath
import com.pp.module_video_details.databinding.ActivityVideoDetailsBinding
import com.pp.mvvm.LifecycleActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val follow = ApiService.api.getFollow2()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.e("TAG", it.code.toString())
                }

        }
    }

    fun onBack(view: View) {
        onBackPressed()
    }
}