package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.RecommendBean
import kotlinx.coroutines.flow.Flow

object RecommendRepository {
    fun getPagingData(): Flow<PagingData<RecommendBean.Item>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecommendPagingSource() }).flow
    }

    private class RecommendPagingSource : PagingSource<String, RecommendBean.Item>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<String, RecommendBean.Item>): String? = null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, RecommendBean.Item> {
            return try {
                val url = params.key ?: EyepetizerService.URL_RECOMMEND
                val recommend = EyepetizerService.service.getRecommend(url)
                val value = recommend.itemList
                val preKey = null
                val nextKey = recommend.nextPageUrl
                LoadResult.Page<String, RecommendBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}