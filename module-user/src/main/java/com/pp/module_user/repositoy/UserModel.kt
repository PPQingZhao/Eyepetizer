package com.pp.module_user.repositoy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_database.user.User
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.library_network.eyepetizer.bean.Message
import com.pp.library_network.eyepetizer.bean.UserInfoBean
import kotlinx.coroutines.*
import kotlinx.coroutines.withContext as withContext1

class UserModel(private val user: User) {

    private val isLogin = MutableLiveData<Boolean>()
    private val loginBean = MutableLiveData<LoginBean>()
    private val userInfoBean = MutableLiveData<UserInfoBean>()
    private var loginJob: Job? = null
    private var getInfoJob: Job? = null

    init {
        loginBean.observeForever {
            if (null == it) {
                return@observeForever
            }
            getInfoJob = GlobalScope.launch(Dispatchers.Main) {
                val response = withContext1(Dispatchers.IO) {
                    EyepetizerService2.userApi.getUserInfo(it.userInfo.uid)
                }

                userInfoBean.value = response.result
            }
        }
    }

    fun userInfo(): LiveData<UserInfoBean> {
        return userInfoBean
    }

    fun isLogin(): LiveData<Boolean> {
        return isLogin
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun login(): LiveData<BaseResponse<LoginBean>> {
        loginJob?.cancel()
        getInfoJob?.cancel()

        val result = MutableLiveData<BaseResponse<LoginBean>>()
        loginJob = GlobalScope.launch(Dispatchers.Main) {
            val response: BaseResponse<LoginBean> = withContext1(Dispatchers.IO) {
                try {
                    EyepetizerService2.userApi.passwordLogin(user.name, user.password)
                } catch (e: Exception) {
                    e.printStackTrace()
                    BaseResponse(-1, Message(e.message, ""), null)
                }
            }

            if (response.code == EyepetizerService2.ErrorCode.SUCCESS) {
                isLogin.value = true
                loginBean.value = response.result
            } else {
                isLogin.value = false
                loginBean.value = null
            }
            result.value = response
        }
        return result
    }
}