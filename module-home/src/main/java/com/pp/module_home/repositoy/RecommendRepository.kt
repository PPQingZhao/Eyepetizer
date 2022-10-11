package com.pp.module_home.repositoy

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.recommend.Item
import kotlinx.coroutines.flow.Flow

object RecommendRepository {
    fun getPagingData(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecommendPagingSource() }).flow
    }

    private class RecommendPagingSource : PagingSource<String, Item>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<String, Item>): String? = null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
            return try {
                val url = params.key ?: EyepetizerService.URL_RECOMMEND
                val recommend = EyepetizerService.service.getRecommend(url)
                val value = recommend.itemList
                val preKey = null
                val nextKey = recommend.nextPageUrl
                LoadResult.Page<String, Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}