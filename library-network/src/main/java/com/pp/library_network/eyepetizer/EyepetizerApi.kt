package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.bean.*
import retrofit2.http.*

interface EyepetizerApi {

    /**
     * topic_hot_feed
     *
     * /v1/card/page/get_page?page_type=card&page_label=topic_hot_feed-2310400673
     */
    @FormUrlEncoded
    @POST(value = EyepetizerService2.BASE_URL_TOPIC_HOT)
    suspend fun getTopicHotPageData(@Field("page_label") page_label: String): BaseResponse<PageDataBean>

    /**
     * http://baobab.kaiyanapp.com/api/v3/lightTopics/internal/704?
     * udid=074347ace25d41df856528937c4a3804eb4fea22&vc=7051610
     * &vn=7.5.161
     * &size=1200X2499
     * &first_channel=huawei
     * &last_channel=huawei
     * &system_version_code=29
     */
    @GET
    suspend fun getLightTopic()

    @GET(value = EyepetizerService2.URL_HOT_QUERIES)
    suspend fun getHotQueries(): BaseResponse<HotQueriesBean>

    @FormUrlEncoded
    @POST(value = EyepetizerService2.URL_GET_PAGE)
    suspend fun getPageData(
        @Field("uid") uid: Int? = 0,
        @Field("page_type") page_type: String? = "card",
        @Field("page_label") page_label: String?,
    ): BaseResponse<PageDataBean>
}