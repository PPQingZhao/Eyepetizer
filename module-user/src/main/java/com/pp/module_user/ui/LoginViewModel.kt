package com.pp.module_user.ui

import android.app.Application
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.library_network.eyepetizer.bean.Message
import com.pp.module_user.manager.UserManager
import com.pp.module_user.repositoy.UserModel
import com.pp.module_user.repositoy.UserRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(app: Application) : LifecycleViewModel(app) {

    val userName = MutableLiveData<String>("17820460461")
    val password = MutableLiveData<String>("zpq940220")
    val loginEnable = ObservableBoolean(false)


    override fun onCreate(owner: LifecycleOwner) {
        userName.observe(owner) {
            loginEnable.set(!TextUtils.isEmpty(userName.value) && !TextUtils.isEmpty(password.value))
        }
        password.observe(owner) {
            loginEnable.set(!TextUtils.isEmpty(userName.value) && !TextUtils.isEmpty(password.value))
        }
    }

    fun login(): LiveData<BaseResponse<LoginBean>> {
        return UserManager.login(userName.value, password.value)
    }
}
