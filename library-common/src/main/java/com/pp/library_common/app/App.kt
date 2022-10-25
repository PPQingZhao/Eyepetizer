package com.pp.library_common.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.common.BuildConfig

open class App : Application() {

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