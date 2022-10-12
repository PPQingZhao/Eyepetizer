package com.pp.module_home

import android.app.Application
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.repository.RecommendRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {
   fun getData(): Flow<PagingData<RecommendBean.Item>> {
      return  RecommendRepository.getPagingData()
    }

}
