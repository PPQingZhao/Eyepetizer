package com.pp.module_video_details

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.module_video_details.databinding.ActivityVideoDetailsBinding
import com.pp.mvvm.LifecycleActivity
import io.reactivex.schedulers.Schedulers

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
        EyepetizerService2.api.getItemDetails(resourceId, resourceType)
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                    return@subscribe
                }
                Log.e("TAG", " ${it.result.video.title}")
            }
    }

    fun onBack(view: View) {
        onBackPressed()
    }
}