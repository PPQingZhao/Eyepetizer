package com.pp.module_search.repository

import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.HotQueriesBean

object SearchRepository {

    suspend fun getData(): BaseResponse<HotQueriesBean> {
        return EyepetizerService2.api.getHotQueries()
    }
}