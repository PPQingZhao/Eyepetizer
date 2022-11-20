package com.pp.module_user.repositoy


import com.pp.library_database.AppDataBase
import com.pp.library_database.user.User
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserRepository {

    private val userDao by lazy {
        AppDataBase.instance.getUserDao()
    }

    suspend fun login(
        userName: String?,
        password: String?
    ): Pair<UserModel, BaseResponse<LoginBean>> {
        val user = User(name = userName, password = password)
        val userModel = UserModel(user)

        // 执行登录逻辑
        val response = userModel.login()
        // 登录成功,保存用户信息到数据库
        if (response.code == EyepetizerService2.ErrorCode.SUCCESS) {
            withContext(Dispatchers.IO) {
                userDao.addUser(user)
            }
        }

        return Pair(userModel, response)
    }

}