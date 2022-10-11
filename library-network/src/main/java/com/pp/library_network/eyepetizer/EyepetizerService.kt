package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.bean.FeedBean
import com.pp.library_network.eyepetizer.bean.FollowBean
import com.pp.library_network.eyepetizer.bean.RecommendBean
import com.pp.library_network.utils.RetrofitUtil
import retrofit2.http.GET
import retrofit2.http.Url

interface EyepetizerService {
    /*
    首页
     每日推荐:  http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
     日报精选: http://baobab.kaiyanapp.com/api/v5/index/tab/feed
     关注:  http://baobab.kaiyanapp.com/api/v6/community/tab/follow

    社区
       推荐:  http://baobab.kaiyanapp.com/api/v7/community/tab/rec

    发现
        发现更多: http://baobab.kaiyanapp.com/api/v7/index/tab/discovery

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
    companion object {
        // 开眼视频 base url
        private const val BASE_URL = "http://baobab.kaiyanapp.com/"

        // 推荐
        const val URL_RECOMMEND = "${BASE_URL}api/v5/index/tab/allRec"

        // 日报精选
        const val URL_FEED = "${BASE_URL}api/v5/index/tab/feed"

        // 关注
        const val URL_FOLLOW = "${BASE_URL}api/v6/community/tab/follow"

        private val retrofit = RetrofitUtil.create(BASE_URL)

        val service: EyepetizerService by lazy { retrofit.create(EyepetizerService::class.java) }
    }

    /**
     * 首页-推荐
     */
    @GET
    suspend fun getRecommend(@Url url: String = URL_RECOMMEND): RecommendBean

    /**
     * 首页-关注
     */
    @GET
    suspend fun geFollow(@Url url: String = URL_FOLLOW): FollowBean

    /**
     * 首页-日报精选
     */
    @GET
    suspend fun getFeed(@Url url: String = URL_FEED): FeedBean

}