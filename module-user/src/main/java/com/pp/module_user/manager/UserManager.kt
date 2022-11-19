package com.pp.module_user.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.module_user.repositoy.UserModel
import com.pp.module_user.repositoy.UserRepository

object UserManager {

    private val userModel = MutableLiveData<UserModel>()

    fun userModel(): LiveData<UserModel> {
        return userModel
    }

    fun login(userName: String?, password: String?): LiveData<BaseResponse<LoginBean>> {
        val loginPair = UserRepository.login(userName, password)

        loginPair.first.isLogin().observeForever(object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                loginPair.first.isLogin().removeObserver(this)
                if (t == true) {
                    userModel.value = loginPair.first
                }
            }
        })

        return loginPair.second
    }

    fun hasUser(): Boolean {
        return false
    }
}