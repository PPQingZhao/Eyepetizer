package com.pp.module_discovery.ui.topic

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.module_discovery.repository.TopicRepository
import com.pp.module_discovery.repository.TopicSquareRepository
import kotlinx.coroutines.flow.Flow

class TopicSquareViewModel(app: Application): ThemeViewModel(app) {

    fun getPagingData(type: String) : Flow<PagingData<Any>> {
        return TopicSquareRepository.getTopicNav(type)
    }
}