package com.pp.module_video_details

import android.app.Application
import androidx.databinding.ObservableField
import com.pp.mvvm.LifecycleViewModel

class IntroductionViewModel(app: Application) : LifecycleViewModel(app) {
    val title = ObservableField<String>("复古风拼贴广告,ASICS的欲火重生,合作退出了复刻巴拿球鞋,鬼冢而而且")
    val category = ObservableField<String>("#广告")
    val content = ObservableField<String>("复古风拼贴广告,ASICS的欲火重生,合作退出了复刻巴拿球鞋,鬼冢而而且，复古风拼贴广告,ASICS的欲火重生,合作退出了复刻巴拿球鞋,鬼冢而而且")
    val collectionCount = ObservableField<String>("68")
    val replyCount = ObservableField<String>("4")
    val shareCount = ObservableField<String>("445")
}
