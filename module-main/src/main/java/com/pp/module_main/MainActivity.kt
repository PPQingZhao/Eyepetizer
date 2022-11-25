package com.pp.module_main

import android.app.Activity
import android.content.Intent
import com.pp.library_base.base.ThemeActivity
import com.pp.module_main.databinding.ActivityMainBinding

class MainActivity : ThemeActivity<ActivityMainBinding, MainViewModel>() {
    override fun getModelClazz(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val mBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}