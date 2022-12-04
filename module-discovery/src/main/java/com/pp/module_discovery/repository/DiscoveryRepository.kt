package com.pp.module_discovery.repository

import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean

object DiscoveryRepository {

    suspend fun getData(): BaseResponse<PageDataBean> {
        val params = mutableMapOf(
            "page_label" to "discover_v2",
            "page_type" to "card"
        )
        return EyepetizerService2.api.getPageData(params)
    }
}