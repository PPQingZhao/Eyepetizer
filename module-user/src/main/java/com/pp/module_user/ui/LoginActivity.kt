package com.pp.module_user.ui

import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.pp.library_base.base.ThemeActivity
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_router_service.services.RouterPath
import com.pp.module_user.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.User.activity_login)
class LoginActivity : ThemeActivity<ActivityLoginBinding, LoginViewModel>() {
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

    var enable = true
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return !enable || super.onTouchEvent(event)
    }

    fun onLogin(view: View) {
        enable = false
        lifecycleScope.launch {
            mViewModel.login()
                .collect { response ->
                    if (response.code != EyepetizerService2.ErrorCode.SUCCESS) {
                        Toast.makeText(baseContext, response.message?.content, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        setResult(RESULT_OK)
                        finish()
                    }
                    enable = true
                }
        }
    }

}