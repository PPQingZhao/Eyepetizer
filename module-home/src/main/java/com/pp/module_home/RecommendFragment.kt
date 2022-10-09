package com.pp.module_home

import com.pp.module_home.databinding.FragmentRecommendBinding
import com.pp.mvvm.LifecycleFragment

class RecommendFragment : LifecycleFragment<FragmentRecommendBinding, RecommendViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_recommend;
    }

    override fun getModelClazz(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }

    override fun onFirstResume() {
        mViewModel.getData()
    }
}

