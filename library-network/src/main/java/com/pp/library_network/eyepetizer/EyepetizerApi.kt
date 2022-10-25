package com.pp.library_network.eyepetizer

import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.URL_FOLLOW
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.URL_GET_PAGE
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.URL_RECOMMEND
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.VERSION
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.VERSION_NAME
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.ItemDetailsBean
import com.pp.library_network.eyepetizer.bean.PageDataBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

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

    @POST
    suspend fun getFollow(
        @Url url: String = URL_FOLLOW
    ): BaseResponse<PageDataBean>

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
    fun getItemDetails(
        @Query("resource_id") resource_id: Int?,
        @Query("resource_type") resource_type: String?
    ): Observable<BaseResponse<ItemDetailsBean>>

    @POST
    suspend fun getRecommend(
        @Url url: String = URL_RECOMMEND
    ): BaseResponse<PageDataBean>
}