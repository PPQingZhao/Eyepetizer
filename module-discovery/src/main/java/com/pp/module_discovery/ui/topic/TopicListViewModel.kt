package com.pp.module_discovery.ui.topic

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.ApiRequest
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.module_discovery.repository.TopicListRepository
import kotlinx.coroutines.flow.Flow

class TopicListViewModel(app: Application): ThemeViewModel(app) {

    fun getPagingData(apiRequest: ApiRequest) : Flow<PagingData<Any>> {
        return TopicListRepository.getTopicList(apiRequest)
    }
}