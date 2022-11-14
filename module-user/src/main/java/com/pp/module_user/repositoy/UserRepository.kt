package com.pp.module_user.repositoy


import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pp.library_database.AppDataBase
import com.pp.library_database.user.User
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object UserRepository {

    private val userDao by lazy {
        AppDataBase.instance.getUserDao()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun login(
        userName: String?,
        password: String?
    ): Pair<UserModel, LiveData<BaseResponse<LoginBean>>> {
        val user = User(name = userName, password = password)
        val userModel = UserModel(user)
        val isLogin = userModel.isLogin()
        //， 监听登录状态
        isLogin.observeForever(object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                isLogin.removeObserver(this)
                // 登录成功,保存用户信息到数据库
                if (t == true) {
                    GlobalScope.launch {
                        userDao.addUser(user)
                    }
                }
            }
        })
        // 执行登录逻辑
        val response = userModel.login()

        return Pair(userModel,response)
    }
}