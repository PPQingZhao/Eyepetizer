package com.pp.module_community.api

import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_community.api.bean.CommunityRecBean
import retrofit2.http.GET
import retrofit2.http.Url

interface CommunityApi {

    companion object {
        val api: CommunityApi by lazy { EyepetizerService.retrofit.create(CommunityApi::class.java) }
    }

    @GET
    suspend fun getRecommend(@Url url: String = EyepetizerService.URL_COMMUNITY_REC): CommunityRecBean
}