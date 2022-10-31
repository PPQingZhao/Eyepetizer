package com.pp.library_common.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.common.BuildConfig
import kotlin.properties.Delegates

open class App : Application() {

    companion object {
        private var mInstance: App by Delegates.notNull()

        fun getInstance(): Application {
            return mInstance
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        mInstance = this
        // init ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        ARouter.getInstance().inject(this)
    }
}