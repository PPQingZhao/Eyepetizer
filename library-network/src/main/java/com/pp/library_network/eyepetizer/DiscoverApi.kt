package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean
import com.pp.library_network.eyepetizer.bean.detail.VideoBean
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DiscoverApi {

    /**
     * 发现标签
     *
     * http://baobab.kaiyanapp.com/api/v6/tag/index?
     * id=1022
     * &udid=1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78
     * &vc=7051610&vn=7.5.161&size=1080X2261
     * &first_channel=xiaomi
     * &last_channel=xiaomi
     * &system_version_code=29
     * &deviceModel=RedmiK305G
     */

    @GET
    suspend fun getTagDetail(@Url url: String, @Query("id") id: String): TagDetailBean

    /**
     * tag videos
     * http://baobab.kaiyanapp.com/api/v1/tag/videos?id=1022&udid=1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78&vc=7051610&vn=7.5.161&size=1080X2261&first_channel=xiaomi&last_channel=xiaomi&system_version_code=29&deviceModel=RedmiK305G
     */

    @GET
    suspend fun getVideos(@Url url: String): VideoBean

    /**
     * http://baobab.kaiyanapp.com/api/v3/lightTopics/internal/711?
     * udid=1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78
     * &vc=7051610&vn=7.5.161&size=1080X2261&first_channel=xiaomi
     * &last_channel=xiaomi&system_version_code=29&deviceModel=RedmiK305G
     */

    @GET
    suspend fun getLightTopic(@Url url: String): VideoBean
}