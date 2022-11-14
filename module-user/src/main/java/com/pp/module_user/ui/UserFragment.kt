package com.pp.module_user.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.FragmentUserBinding
import com.pp.mvvm.LifecycleFragment

@Route(path = RouterPath.User.fragment_user)
class UserFragment : LifecycleFragment<FragmentUserBinding, UserViewModel>() {
    override val mBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<UserViewModel> {
        return UserViewModel::class.java
    }
}