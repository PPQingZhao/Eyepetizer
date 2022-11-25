package com.pp.module_home.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_home.repository.DailyRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.flow.Flow

class DailyViewModel(app:Application) : ThemeViewModel(app) {
    fun getData(): Flow<PagingData<Metro>> {
      return DailyRepository.getPagingData()
    }
}
