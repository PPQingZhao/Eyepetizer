package com.pp.module_search.ui

import android.app.Application
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.HotQueriesBean
import com.pp.module_search.repository.SearchRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class SearchViewModel(app: Application): LifecycleViewModel(app) {

    suspend fun getHotQueries(): BaseResponse<HotQueriesBean> {
        return SearchRepository.getData()
    }
}