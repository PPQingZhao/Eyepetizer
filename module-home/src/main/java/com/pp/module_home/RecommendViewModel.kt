package com.pp.module_home

import android.app.Application
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.bean.RecommendBean
import com.pp.module_home.repository.RecommendRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {
    val content = ObservableField<String>("推荐")
   fun getData(): Flow<PagingData<RecommendBean.Item>> {
      return  RecommendRepository.getPagingData()
    }

}
