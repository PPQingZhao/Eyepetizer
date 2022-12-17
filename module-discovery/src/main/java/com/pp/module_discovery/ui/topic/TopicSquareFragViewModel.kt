package com.pp.module_discovery.ui.topic

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_discovery.repository.TopicSquareRepository
import kotlinx.coroutines.flow.Flow

class TopicSquareFragViewModel(app: Application): ThemeViewModel(app) {

    fun getPagingData(pageLabel: String, pageType: String) : Flow<PagingData<Any>> {
        return TopicSquareRepository.getPagingData(pageLabel, pageType)
    }
}