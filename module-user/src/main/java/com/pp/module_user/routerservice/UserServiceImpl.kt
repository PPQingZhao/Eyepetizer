package com.pp.module_user.routerservice

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.IUserService
import com.pp.library_router_service.services.RouterServiceImpl
import com.pp.module_user.manager.UserManager

@Route(path = RouterServiceImpl.User.SERVICE_USER)
class UserServiceImpl : IUserService {
    override fun hasUser(): Boolean {
        return UserManager.hasUser()
    }

    override fun getUid(): Int? {
        return UserManager.userModel().value?.userInfo()?.value?.uid
    }

    override fun init(context: Context?) {
    }
}