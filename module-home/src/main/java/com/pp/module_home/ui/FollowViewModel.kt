package com.pp.module_home.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_home.repository.FollowRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class FollowViewModel(app: Application) : LifecycleViewModel(app) {
    fun getData(): Flow<PagingData<Metro>> {
       return FollowRepository.getPagingData()
    }
}
