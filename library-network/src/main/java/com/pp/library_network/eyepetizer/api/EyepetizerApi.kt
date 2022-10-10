package com.pp.library_network.eyepetizer.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface EyepetizerApi {
    /*
    首页
     发现更多: http://baobab.kaiyanapp.com/api/v7/index/tab/discovery
     每日推荐:  http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
     日报精选: http://baobab.kaiyanapp.com/api/v5/index/tab/feed

    社区
       推荐:  http://baobab.kaiyanapp.com/api/v7/community/tab/rec
       关注:  http://baobab.kaiyanapp.com/api/v6/community/tab/follow

    通知
        主题： http://baobab.kaiyanapp.com/api/v7/tag/tabList
        通知：  http://baobab.kaiyanapp.com/api/v3/messages
        互动：  http://baobab.kaiyanapp.com/api/v7/topic/list

     视频详情页
        相关推荐：http://baobab.kaiyanapp.com/api/v4/video/related?id=186856
                 ======>>当前播放视频的id，从跳转页面视频item中获取
        评论：http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=186856
                 ======>> 当前播放视频的id，从跳转页面视频item中获取
     */

    @GET("api/v6/community/tab/follow")
    fun follow(): Call<ResponseBody>

    @GET("api/v5/index/tab/allRec")
    fun recommend(): Call<ResponseBody>

    @GET("api/v5/index/tab/feed")
    fun feed(): Call<ResponseBody>
}