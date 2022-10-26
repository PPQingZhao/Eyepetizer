package com.pp.module_community.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.module_community.model.MultiItemEntity
import com.pp.module_community.respository.SquareRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class SquareViewModel(app: Application) : LifecycleViewModel(app) {

    fun getData(): Flow<PagingData<MultiItemEntity>> {
        return SquareRepository.getPagingData()
    }

}