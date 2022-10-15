package com.pp.module_community.respository

import android.util.Log
import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_community.api.CommunityApi
import com.pp.module_community.api.bean.CommunityRecBean
import kotlinx.coroutines.flow.Flow

object CommunityRecRepository {

    private const val TAG = "CommunityRecRepository"

    fun getPagingData(): Flow<PagingData<CommunityRecBean.Item>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecPagingSource() }).flow
    }

    private class RecPagingSource : PagingSource<String, CommunityRecBean.Item>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<String, CommunityRecBean.Item>): String? =
            null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, CommunityRecBean.Item> {
            return try {
                val url = params.key ?: EyepetizerService.URL_COMMUNITY_REC
                val recommendBean = CommunityApi.api.getRecommend(url)
                val value = recommendBean.itemList
                val preKey = null
                val nextKey = recommendBean.nextPageUrl
                LoadResult.Page<String, CommunityRecBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                Log.e(TAG, "load error: ${e.message}")
                LoadResult.Error(e)
            }
        }

    }
}