package com.pp.module_user.repositoy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_database.user.User
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.library_network.eyepetizer.bean.Message
import com.pp.library_network.eyepetizer.bean.UserInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class UserModel(private val user: User) {

    private val isLogin = MutableLiveData<Boolean>()
    private val loginBean = MutableLiveData<LoginBean>()
    private val userInfoBean = MutableLiveData<UserInfoBean>()

    companion object {
        private val TAG = "UserModel"
    }

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

    fun login(): Flow<BaseResponse<LoginBean>> {
        return flow<BaseResponse<LoginBean>> {
            Log.v(TAG, "start login: ${user.name}")
            // 执行登录逻辑
            val response = EyepetizerService2.userApi.passwordLogin(user.name, user.password)
            emit(response)

        }.onEach { response ->
            Log.v(TAG, "login code: ${response.code}")
            if (response.code == EyepetizerService2.ErrorCode.SUCCESS) {

                withContext(Dispatchers.Main) {
                    isLogin.value = true
                    loginBean.value = response.result
                }

                // 获取用户信息
                val getUserInfo =
                    EyepetizerService2.userApi.getUserInfo(loginBean.value?.userInfo?.uid)
                withContext(Dispatchers.Main) {
                    userInfoBean.value = getUserInfo.result
                }

            } else {
                withContext(Dispatchers.Main) {
                    isLogin.value = false
                    loginBean.value = null
                    userInfoBean.value = null
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun logout(): Flow<BaseResponse<String>> {
        return flow {
            Log.v(TAG, "start logout: ${user.name}")
            emit(BaseResponse(0, null, ""))
        }
    }

}