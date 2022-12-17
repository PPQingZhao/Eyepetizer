package com.pp.module_discovery.ui.topic

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.module_discovery.repository.TopicRepository
import kotlinx.coroutines.flow.Flow

class TopicViewModel(app: Application): ThemeViewModel(app) {

    fun getPagingData(url: String) : Flow<PagingData<Item>> {
        return TopicRepository.getTopic(url)
    }
}