package com.pp.module_home.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.module_home.repository.RecommendRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {
   fun getPageData(): Flow<PagingData<Any>> {
      return  RecommendRepository.getPagingData()
    }

}
