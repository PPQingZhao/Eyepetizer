package com.pp.module_main

import com.pp.library_base.base.ThemeFragment
import com.pp.module_main.databinding.FragmentTestBinding
import com.pp.library_base.base.ThemeViewModel

class TestFragment : ThemeFragment<FragmentTestBinding, ThemeViewModel>() {
    override val mBinding: FragmentTestBinding by lazy {
        FragmentTestBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeViewModel> {
        return ThemeViewModel::class.java
    }

    override fun onFirstResume() {

        mBinding.text = "test"

    }
}