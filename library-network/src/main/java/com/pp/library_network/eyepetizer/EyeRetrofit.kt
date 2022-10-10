package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.api.EyepetizerApi
import com.pp.library_network.utils.RetrofitUtil

object EyeRetrofit {
    val retrofit = RetrofitUtil.create(EyeRequest.base_url)

    val eyepetizerApi = retrofit.create(EyepetizerApi::class.java)
}