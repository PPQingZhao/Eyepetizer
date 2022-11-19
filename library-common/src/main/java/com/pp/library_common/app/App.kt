package com.pp.library_common.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.pp.common.BuildConfig
import com.pp.library_router_service.services.IAppService
import com.pp.library_router_service.services.RouterServiceImpl
import kotlin.properties.Delegates

open class App : Application() {

    companion object {
        private var mInstance: App by Delegates.notNull()

        fun getInstance(): Application {
            return mInstance
        }
    }

    @Autowired(name = RouterServiceImpl.DataBase.DATABASE_APP)
    lateinit var dataBaseAppService: IAppService

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

    override fun onCreate() {
        super.onCreate()
        // 数据库模块
        dataBaseAppService.onCreate(this)
    }
}