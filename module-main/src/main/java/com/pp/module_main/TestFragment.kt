package com.pp.module_main

import com.pp.module_main.databinding.FragmentTestBinding
import com.pp.mvvm.LifecycleFragment
import com.pp.mvvm.LifecycleViewModel

class TestFragment : LifecycleFragment<FragmentTestBinding, LifecycleViewModel>() {
    override val mBinding: FragmentTestBinding by lazy {
        FragmentTestBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<LifecycleViewModel> {
        return LifecycleViewModel::class.java
    }

    override fun onFirstResume() {

        mBinding.text = "test"

    }
}