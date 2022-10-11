package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.FeedBean
import kotlinx.coroutines.flow.Flow

object FeedRepository {
    fun getPagingData(): Flow<PagingData<FeedBean.Item>> {
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
                val feedBean = EyepetizerService.service.getFeed(url)
                val value = feedBean.itemList
                val preKey = null
                val nextKey = feedBean.nextPageUrl
                LoadResult.Page<String, FeedBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}