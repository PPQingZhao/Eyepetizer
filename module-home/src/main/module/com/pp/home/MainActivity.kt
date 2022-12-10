package com.pp.home

import android.os.Bundle
import com.pp.library_base.base.ThemeActivity
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_home.R
import com.pp.module_home.databinding.ActivityMainBinding

class MainActivity : ThemeActivity<ActivityMainBinding,ThemeViewModel>() {

    override val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<ThemeViewModel> {
        return ThemeViewModel::class.java
    }
}