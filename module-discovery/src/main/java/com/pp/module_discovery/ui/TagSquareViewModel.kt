package com.pp.module_discovery.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.library_network.eyepetizer.bean.detail.Item
import com.pp.module_discovery.repository.TagDetailRepository
import kotlinx.coroutines.flow.Flow

class TagSquareViewModel(app : Application) : ThemeViewModel(app) {

    suspend fun getDynamicsPagingData(url: String) : Flow<PagingData<Item>> {
        return TagDetailRepository.getVideoPagingData(url)
    }
}