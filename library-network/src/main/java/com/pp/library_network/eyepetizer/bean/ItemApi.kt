package com.pp.library_network.eyepetizer.bean

import com.pp.library_network.eyepetizer.EyepetizerService2
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ItemApi {
    /**
     * 评论
     */
    @FormUrlEncoded
    @POST("v1/item/comment/get_cms_comment_list")
    suspend fun getCMSCommentList(
        @Field("resource_id") resource_id: Int?,
        @Field("resource_type") resource_type: String?,
        @Field("sort_type") sort_type: String? = EyepetizerService2.SortType.SORT_TYPE_HOT,
        @Field("last_item_id") last_item_id: Int?
    ): BaseResponse<CommentsBean>


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
        @Query("resource_id") resource_id: Long?,
        @Query("resource_type") resource_type: String?
    ): BaseResponse<MetroDataBean>

    /**
     * 相关推荐
     */

    @FormUrlEncoded
    @POST("v1/content/item/get_related_recommend")
    suspend fun getRelatedRecommend(
        @Field("resource_id") resource_id: Long?,
        @Field("resource_type") resource_type: String?
    ): BaseResponse<RelatedRecommendBean>
}