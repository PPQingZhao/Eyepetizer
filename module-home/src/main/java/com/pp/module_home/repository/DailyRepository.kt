package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_common.pagingsource.ItemModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object DailyRepository {
    /* fun getPagingData(): Flow<PagingData<FeedBean.Item>> {
         return Pager(
             config = PagingConfig(15),
             pagingSourceFactory = { FeedPagingSource() }).flow
     }

     private class FeedPagingSource : PagingSource<String, FeedBean.Item>() {
         @ExperimentalPagingApi
         override fun getRefreshKey(state: PagingState<String, FeedBean.Item>): String? = null

         override suspend fun load(params: LoadParams<String>): LoadResult<String, FeedBean.Item> {
             return try {
                 val url = params.key ?: EyepetizerService.URL_FEED
                 val feedBean = HomeApi.api.getFeed(url)
                 val value = feedBean.itemList
                 val preKey = null
                 val nextKey = feedBean.nextPageUrl
                 LoadResult.Page<String, FeedBean.Item>(value, preKey, nextKey)
             } catch (e: Exception) {
                 LoadResult.Error(e)
             }
         }
     }*/

    fun getPagingData(): Flow<PagingData<ItemModel<Any>>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { DailyPagingSource() }).flow
    }

    private class DailyPagingSource : MetroPagingSource() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(key ?: EyepetizerService2.URL_DAILY)
        }

    }
}