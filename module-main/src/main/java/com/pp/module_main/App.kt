package com.pp.module_main

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // init ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        ARouter.getInstance().inject(this)
    }
}