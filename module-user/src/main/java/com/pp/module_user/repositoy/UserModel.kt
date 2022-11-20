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
import kotlinx.coroutines.withContext

class UserModel(private val user: User) {

    private val isLogin = MutableLiveData<Boolean>()
    private val loginBean = MutableLiveData<LoginBean>()
    private val userInfoBean = MutableLiveData<UserInfoBean>()


    fun userInfo(): LiveData<UserInfoBean> {
        return userInfoBean
    }

    fun isLogin(): LiveData<Boolean> {
        return isLogin
    }

    fun getName(): String? {
        return user.name
    }

    fun getPassword(): String? {
        return user.password
    }

    suspend fun login(): BaseResponse<LoginBean> {

        return withContext(Dispatchers.Main) {
            val response: BaseResponse<LoginBean> = withContext(Dispatchers.IO) {
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

                val getUserInfo =
                    withContext(Dispatchers.IO) { EyepetizerService2.userApi.getUserInfo(loginBean.value?.userInfo?.uid) }
                userInfoBean.value = getUserInfo.result
            } else {
                isLogin.value = false
                loginBean.value = null
            }

            response
        }
    }
}