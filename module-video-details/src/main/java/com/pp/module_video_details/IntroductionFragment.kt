package com.pp.module_video_details

import com.pp.module_video_details.databinding.FragmentIntroductionBinding
import com.pp.mvvm.LifecycleFragment

class IntroductionFragment :
    LifecycleFragment<FragmentIntroductionBinding, IntroductionViewModel>() {
    override val mBinding by lazy { FragmentIntroductionBinding.inflate(layoutInflater) }
    override fun getModelClazz(): Class<IntroductionViewModel> {
        return IntroductionViewModel::class.java
    }

}