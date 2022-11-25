package com.pp.module_discovery.repository

import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_network.eyepetizer.bean.detail.TagDetailBean

object DiscoveryRepository {

    suspend fun getData(): BaseResponse<PageDataBean> {
        val params = mutableMapOf(
            "page_label" to "discover_v2",
            "page_type" to "card"
        )
        return EyepetizerService2.api.getPageData(params)
    }

    suspend fun getTagDetail(id: String): TagDetailBean {
        return EyepetizerService.discoverApi.getTagDetail(EyepetizerService.URL_TAG, id)
    }
}