package com.pp.module_home.ui

import android.app.Application
import androidx.paging.PagingData
import com.pp.library_base.base.ThemeViewModel
import com.pp.module_home.repository.RecommendRepository
import kotlinx.coroutines.flow.Flow

class RecommendViewModel(app: Application) : ThemeViewModel(app) {
   fun getPageData(): Flow<PagingData<Any>> {
      return  RecommendRepository.getPagingData()
    }

}
