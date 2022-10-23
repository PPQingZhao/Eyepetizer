package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_network.utils.RetrofitUtil
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    companion object {

        const val BASE_URL_V1 = "http://api.eyepetizer.net/"

        const val URL_GET_PAGE = "/v1/card/page/get_page"

        val retrofit = RetrofitUtil.create(BASE_URL_V1)

        const val APP_ID = "ahpagrcrf2p7m6rg"
        const val CID = "53db07b41302a7a3e569a27993ff4f13"
        const val API_KEY = "0530ee4341324ce2b26c23fcece80ea2"

        const val AUTH = "uTWtTYVSWAl50Kx2La5nLZrSIox+zS1gRB/KtdP3lBcwWwxRDekPW2EDMmT2AE9WJJ0QbqmSzZXLLmZkJXsa/dj0xNc8GZ0WXf20PLYH+uB3ePfz2u7d+7GSsc0vLkXiiV9vdZtq+rnu6cF4EMCaMIQz1Uf7PPQNia3IdPbC11BdssO5yMx9y/Scytg8tzFmAmaoZ0hSj3cjkOHMlUStSWOoiBClbaY+VsR7cum/pCyRQgJxFCOmh5MWnTdA5RNgF8/3JEBQ06qyClVCCjaXfA=="
        const val UA = "EYEPETIZER/7051610 (Redmi K30 5G;android;10;zh_CN;android;7.5.161;cn-bj;xiaomi;53db07b41302a7a3e569a27993ff4f13;WIFI;1080*2261) native/1.0"

        const val VERSION = 7051610
        const val VERSION_NAME = "7.5.161"

        val api: ApiService by lazy { retrofit.create(ApiService::class.java) }


        /**
         * udid=1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78&vc=7051610&vn=7.5.161&deviceModel=Redmi%20K30%205G&first_channel=xiaomi
         * &size=1080X2261&system_version_code=29&token=ea9cd264f05e6e14&page_type=card&page_label=follow
         */
    }

    @POST
    suspend fun getPageData(
        @Url url: String = URL_GET_PAGE,
        @Query("udid") udid: String = "1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78",
        @Query("vc") vc: Int = VERSION,
        @Query("vn") vn: String = VERSION_NAME,
        @Query("first_channel") first_channel: String = "xiaomi",
        @Query("size") size: String = "1080X2261",
        @Query("token") token: String = "ea9cd264f05e6e14",
        @Query("page_type") page_type: String = "card",
        @Query("page_label") page_label: String = "follow",
        @Query("system_version_code") system_version_code: Int = 29,
        @Query("deviceModel") deviceModel: String = "Redmi%20K30%205G"
    ): BaseResponse<PageDataBean>

}