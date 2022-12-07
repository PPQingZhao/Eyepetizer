package com.pp.module_discovery.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_discovery.repository.DiscoveryRepository
import kotlinx.coroutines.flow.Flow

class DiscoveryViewModel(app: Application) : ThemeViewModel(app) {

    fun  getPagingData(): Flow<PagingData<Any>> {
        return DiscoveryRepository.getPagingData()
    }

}