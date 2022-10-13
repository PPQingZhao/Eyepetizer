package com.pp.module_community.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.module_community.api.bean.CommunityRecBean
import com.pp.module_community.respository.CommunityRecRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {

    fun getData(): Flow<PagingData<CommunityRecBean.Item>> {
        return CommunityRecRepository.getPagingData()
    }
}