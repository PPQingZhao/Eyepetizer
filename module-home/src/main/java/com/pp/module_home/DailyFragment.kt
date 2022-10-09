package com.pp.module_home

import com.pp.module_home.databinding.FragmentDailyBinding
import com.pp.mvvm.LifecycleFragment

class DailyFragment : LifecycleFragment<FragmentDailyBinding, DailyViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_daily;
    }

    override fun getModelClazz(): Class<DailyViewModel> {
        return DailyViewModel::class.java
    }

    override fun onFirstResume() {
        mViewModel.getData()
    }
}

