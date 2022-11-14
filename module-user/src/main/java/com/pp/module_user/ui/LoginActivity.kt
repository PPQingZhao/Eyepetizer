package com.pp.module_user.ui

import android.view.View
import android.widget.Toast
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.ActivityLoginBinding
import com.pp.mvvm.LifecycleActivity

@Route(path = RouterPath.User.activity_login)
class LoginActivity : LifecycleActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getModelClazz(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override val mBinding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(
            layoutInflater
        )
    }


    fun onBack(view: View) {
        finish()
    }

    fun onLogin(view: View) {
        mViewModel.login().observe(this) {
            if (it.code != EyepetizerService2.ErrorCode.SUCCESS) {
                Toast.makeText(baseContext, it.message?.content, Toast.LENGTH_SHORT).show()
            } else {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

}