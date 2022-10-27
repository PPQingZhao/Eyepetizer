package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.URL_GET_PAGE
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.VERSION
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.VERSION_NAME
import com.pp.library_network.eyepetizer.bean.*
import retrofit2.http.*

interface EyepetizerApi {

    /**
     * udid=1e91ce09fe7f44d1bbeb483ffc1ab25fd5170d78&vc=7051610&vn=7.5.161&deviceModel=Redmi%20K30%205G&first_channel=xiaomi
     * &size=1080X2261&system_version_code=29&token=ea9cd264f05e6e14&page_type=card&page_label=follow
     */
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

    @POST
    suspend fun getPageData(@Url url: String): BaseResponse<PageDataBean>


    /*
        http://api.eyepetizer.net/v1/content/item/get_item_detail_v2
        ?resource_id=314443
        &resource_type=pgc_video
        &udid=074347ace25d41df856528937c4a3804eb4fea22
        &vc=7051610
        &vn=7.5.161
        &deviceModel=ELS-AN00
        &size=1200X2499
        &first_channel=huawei
        &last_channel=huawei
        &system_version_code=29
        &token=43f18a526760c43d
     */

    @GET("v1/content/item/get_item_detail_v2")
    suspend fun getItemDetails(
        @Query("resource_id") resource_id: Int?,
        @Query("resource_type") resource_type: String?
    ): BaseResponse<ItemDetailsBean>

    /**
     * 相关推荐
     */
    @POST("v1/content/item/get_related_recommend")
    suspend fun getRelatedRecommend(
        @Query("resource_id") resource_id: Int?,
        @Query("resource_type") resource_type: String?
    ): BaseResponse<RelatedRecommendBean>


    /**
     * 评论
     */
    @POST("v1/item/comment/get_cms_comment_list")
    suspend fun getCMSCommentList(
        @Query("resource_id") resource_id: Int?,
        @Query("resource_type") resource_type: String?,
        @Query("sort_type") sort_type: String? = EyepetizerService2.SORT_TYPE_HOT
    ): BaseResponse<CommentsBean>

}