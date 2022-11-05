package com.pp.module_user.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.ActivityLoginBinding
import com.pp.mvvm.LifecycleActivity

@Route(path = RouterPath.User.activity_login)
class LoginActivity : LifecycleActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getModelClazz(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override val mBinding: ActivityLoginBinding get() = ActivityLoginBinding.inflate(layoutInflater)
}