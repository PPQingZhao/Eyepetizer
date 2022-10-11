package com.pp.module_home

import android.app.Application
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.EyeRetrofit
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.feed.Item
import com.pp.module_home.repositoy.FeedRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyViewModel(app:Application) : LifecycleViewModel(app) {
    fun getData(): Flow<PagingData<Item>> {
      return FeedRepository.getPagingData()
    }
}
