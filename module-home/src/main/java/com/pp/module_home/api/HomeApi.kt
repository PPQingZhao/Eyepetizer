package com.pp.module_home.api

import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_home.api.bean.FeedBean
import com.pp.module_home.api.bean.FollowBean
import com.pp.module_home.api.bean.RecommendBean
import retrofit2.http.GET
import retrofit2.http.Url

interface HomeApi {

    companion object{
        val api: HomeApi by lazy { EyepetizerService.retrofit.create(HomeApi::class.java) }
    }

    /**
     * 首页-推荐
     */
    @GET
    suspend fun getRecommend(@Url url: String = EyepetizerService.URL_RECOMMEND): RecommendBean

    /**
     * 首页-关注
     */
    @GET
    suspend fun geFollow(@Url url: String = EyepetizerService.URL_FOLLOW): FollowBean

    /**
     * 首页-日报精选
     */
    @GET
    suspend fun getFeed(@Url url: String = EyepetizerService.URL_FEED): FeedBean
}