package com.pp.module_user.routerservice

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.IAppService
import com.pp.library_router_service.services.RouterServiceImpl
import com.pp.module_user.manager.UserManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Route(path = RouterServiceImpl.User.SERVICE_APP)
class UserAppImpl : IAppService {
    override fun onCreate(application: Application) {
        // 登录记录的用户
        GlobalScope.launch {
            UserManager.loginExistUser()
        }
    }

    override fun init(context: Context?) {
    }
}