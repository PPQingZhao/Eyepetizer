package com.pp.module_search.repository

import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.HotQueriesBean
import com.pp.library_network.eyepetizer.bean.PageDataBean

object SearchRepository {

    suspend fun getHotQueries(): BaseResponse<HotQueriesBean> {
        return EyepetizerService2.api.getHotQueries()
    }

    suspend fun getRecommend(): BaseResponse<PageDataBean> {
        return EyepetizerService2.api.getSearchRecommend()
    }
}