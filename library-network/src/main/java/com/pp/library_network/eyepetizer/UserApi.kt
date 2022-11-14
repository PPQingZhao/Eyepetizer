package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoginBean
import com.pp.library_network.eyepetizer.bean.UserInfoBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    /**
     * 密码账号登录
     */
    @FormUrlEncoded
    @POST(value = EyepetizerService2.BASE_URL_PASSWORD_LOGIN)
    suspend fun passwordLogin(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("user_type") user_type: String? = "ugc",
    ): BaseResponse<LoginBean>


    /**
     * 获取用户信息:http://api.eyepetizer.net/v1/user/center/get_user_info?uid=304922815
     */
    @FormUrlEncoded
    @POST(value = EyepetizerService2.BASE_URL_GET_USER_INFO)
    suspend fun getUserInfo(@Field("uid") uid: Int?): BaseResponse<UserInfoBean>
}