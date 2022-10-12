package com.pp.module_home

import android.app.Application
import androidx.paging.PagingData
import com.pp.module_home.api.bean.FollowBean.Item
import com.pp.module_home.repository.FollowRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class FollowViewModel(app: Application) : LifecycleViewModel(app) {
    fun getData(): Flow<PagingData<Item>> {
       return FollowRepository.getPagingData()
    }
}
