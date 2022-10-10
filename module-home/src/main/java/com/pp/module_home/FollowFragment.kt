package com.pp.module_home

import com.pp.module_home.databinding.FragmentFollowBinding
import com.pp.mvvm.LifecycleFragment

class FollowFragment : LifecycleFragment<FragmentFollowBinding, FollowViewModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_follow;
    }

    override fun getModelClazz(): Class<FollowViewModel> {
        return FollowViewModel::class.java
    }

    override fun onFirstResume() {
        mViewModel.getData()
    }

}

