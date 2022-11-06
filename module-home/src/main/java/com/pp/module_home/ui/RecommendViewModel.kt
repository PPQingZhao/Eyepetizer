package com.pp.module_home.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pp.module_home.repository.RecommendRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cache

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {
   fun getPageData(): LiveData<PagingData<Any>> {
      return  RecommendRepository.getPagingData().asLiveData()
    }

}
