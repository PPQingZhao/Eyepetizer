package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_home.api.bean.FollowBean.Item
import com.pp.module_home.api.HomeApi
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
                val followBean = HomeApi.api.geFollow(url)
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