package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_network.eyepetizer.bean.FollowBean.Item
import kotlinx.coroutines.flow.Flow

object FollowRepository {
    fun getPagingData(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { FollowPagingSource() }).flow
    }

    private class FollowPagingSource : PagingSource<String, Item>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<String, Item>): String? = null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
            return try {
                val url = params.key ?: EyepetizerService.URL_FOLLOW
                val followBean = EyepetizerService.service.geFollow(url)
                val value = followBean.itemList
                val preKey = null
                val nextKey = followBean.nextPageUrl
                LoadResult.Page<String, Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}