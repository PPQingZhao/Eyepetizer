package com.pp.module_home.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.module_home.repository.FollowRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class FollowViewModel(app: Application) : LifecycleViewModel(app) {
    fun getData(): Flow<PagingData<MetroFollowItemViewModel>> {
       return FollowRepository.getPagingData()
    }
}
