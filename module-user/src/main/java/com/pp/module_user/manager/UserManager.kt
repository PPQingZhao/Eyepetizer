package com.pp.module_user.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pp.library_common.app.App
import com.pp.library_common.datastore.dataStore
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.module_user.repositoy.UserModel
import com.pp.module_user.repositoy.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

object UserManager {

    private val userModel = MutableLiveData<UserModel?>()

    private var hasUser = false
    private val userNameKey = stringPreferencesKey("user_name")
    private val passwordKey = stringPreferencesKey("password")

    fun userModel(): LiveData<UserModel?> {
        return userModel
    }

    suspend fun loginExistUser() {
        App.getInstance().baseContext.dataStore.data.first().apply {
            val userName = get(userNameKey)
            val password = get(passwordKey)
            if (userName?.isEmpty() != false) {
                return
            }

            if (password?.isEmpty() != false) {
                return
            }

            login(userName, password).collect()
        }
    }

    @OptIn(FlowPreview::class)
    fun login(
        userName: String?,
        password: String?,
    ): Flow<BaseResponse<LoginBean>> {
        // 先登出
        return logout()
            .flatMapConcat {
                // 登录用户
                UserRepository.login(userName, password).onEach { loginPair ->
                    if (loginPair.second.code == EyepetizerService2.ErrorCode.SUCCESS) {
                        val model = loginPair.first
                        withContext(Dispatchers.Main) {
                            userModel.value = model
                        }
                        App.getInstance().baseContext.dataStore.edit {
                            it[userNameKey] = model.getName().toString()
                            it[passwordKey] = model.getPassword().toString()
                        }
                        hasUser = true
                    }
                }.map {
                    it.second
                }
            }
    }

    fun logout(): Flow<BaseResponse<String>> {
        return if (userModel.value == null) {
            flow {
                emit(BaseResponse(0, null, null))
            }
        } else {
            UserRepository.logout(userModel.value!!)
        }.onStart {
            withContext(Dispatchers.Main) {
                userModel.value = null
            }
            App.getInstance().baseContext.dataStore.edit {
                it[userNameKey] = ""
                it[passwordKey] = ""
            }
            hasUser = false
        }
    }

    fun hasUser(): Boolean {
        return hasUser
    }
}