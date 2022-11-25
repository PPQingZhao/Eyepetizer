package com.pp.module_community.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_common.model.MultiItemEntity
import com.pp.module_community.respository.SquareRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.flow.Flow

class SquareViewModel(app: Application) : ThemeViewModel(app) {

    fun getData(): Flow<PagingData<MultiItemEntity>> {
        return SquareRepository.getPagingData()
    }

}