package com.pp.module_main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.pp.library_base.base.ThemeActivity
import com.pp.module_main.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : ThemeActivity<ActivitySplashBinding, SplashViewModel>() {
    override val mBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun getModelClazz(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            MainActivity.start(this)
            finish()
        }, 300)

    }
}