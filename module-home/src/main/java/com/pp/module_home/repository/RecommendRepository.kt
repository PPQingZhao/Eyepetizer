package com.pp.module_home.repository

import android.util.Log
import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.module_home.api.bean.RecommendBean
import com.pp.module_home.api.HomeApi
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
                val recommend = HomeApi.api.getRecommend(url)
                val value = mutableListOf<RecommendBean.Item>()
                // 遍历: 将 ItemCollection 类型里面的itemList 取出，添加到recyclerview item中，这样方便recyclerview处理
                recommend.itemList.forEach {

                    // ItemCollection
                    if (EyepetizerService.ItemDataType.ITEM_COLLECTION == EyepetizerService.ItemDataType.getItemDataType(it.data.dataType)) {
                        Log.e("RecommendPagingSource", "将squareCardCollection size: ${it.data.itemList.size}")
                        value.addAll(it.data.itemList)
                    } else {
                        value.add(it)
                    }
                }

                val preKey = null
                val nextKey = recommend.nextPageUrl
                LoadResult.Page<String, RecommendBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}