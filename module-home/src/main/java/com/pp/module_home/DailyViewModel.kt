package com.pp.module_home

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.bean.feed.Item
import com.pp.module_home.repository.FeedRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class DailyViewModel(app:Application) : LifecycleViewModel(app) {
    fun getData(): Flow<PagingData<Item>> {
      return FeedRepository.getPagingData()
    }
}
