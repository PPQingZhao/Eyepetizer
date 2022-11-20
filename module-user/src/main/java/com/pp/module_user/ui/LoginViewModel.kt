package com.pp.module_user.ui

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.module_user.manager.UserManager
import com.pp.mvvm.LifecycleViewModel

@Suppress("COMPATIBILITY_WARNING")
class LoginViewModel(app: Application) : LifecycleViewModel(app) {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginEnable = ObservableBoolean(false)

    override fun onCreate(owner: LifecycleOwner) {
        userName.value = UserManager.userModel().value?.getName()
        password.value = UserManager.userModel().value?.getPassword()

        val observer = { v: String? ->
            loginEnable.set(!TextUtils.isEmpty(userName.value) && !TextUtils.isEmpty(password.value))
        }
        userName.observe(owner, observer)
        password.observe(owner, observer)
    }


    suspend fun login(context: Context): BaseResponse<LoginBean> {
        return UserManager.login(context,userName.value, password.value)
    }
}
