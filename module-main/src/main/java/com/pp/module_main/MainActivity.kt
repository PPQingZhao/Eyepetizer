package com.pp.module_main

import com.pp.module_main.databinding.ActivityMainBinding
import com.pp.mvvm.LifecycleActivity

class MainActivity : LifecycleActivity<ActivityMainBinding, MainViewModel>() {
    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val mBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

}