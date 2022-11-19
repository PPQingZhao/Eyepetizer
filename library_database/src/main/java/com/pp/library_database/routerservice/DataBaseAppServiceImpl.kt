package com.pp.library_database.routerservice

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_database.AppDataBase
import com.pp.library_router_service.services.IAppService
import com.pp.library_router_service.services.RouterServiceImpl

/**
 * 监听app 生命周期
 */
@Route(path = RouterServiceImpl.DataBase.DATABASE_APP)
class DataBaseAppServiceImpl : IAppService {
    override fun onCreate(application: Application) {
        // 初始化数据库
        ProcessLifecycleOwner.get().lifecycle.addObserver(
            AppDataBase
                .Companion
                .ProcessDatabaseInitializer(application)
        )
    }

    override fun init(context: Context?) {
    }
}