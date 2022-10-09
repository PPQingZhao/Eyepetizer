package com.pp.module_home

import com.pp.module_home.databinding.FragmentDiscoverBinding
import com.pp.mvvm.LifecycleFragment

class DiscoverFragment : LifecycleFragment<FragmentDiscoverBinding, DiscoverViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_discover;
    }

    override fun getModelClazz(): Class<DiscoverViewModel> {
        return DiscoverViewModel::class.java
    }

    override fun onFirstResume() {
        mViewModel.getData()
    }

}

