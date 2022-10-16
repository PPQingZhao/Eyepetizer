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

    private class RecommendPagingSource : PagingSource<Int, RecommendBean.Item>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<Int, RecommendBean.Item>): Int? = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendBean.Item> {
            return try {
                val page = params.key ?: 0
                Log.e("RecommendPagingSource", "page: ${page}")
                val recommend = HomeApi.api.getRecommend(page)
                Log.e("RecommendPagingSource", "nextPageUrl: ${recommend.nextPageUrl}")

                val value = mutableListOf<RecommendBean.Item>()

                // 没有数据
                if (recommend.count == 0) {
                    return LoadResult.Error(Throwable())
                }

                // 遍历: 将 ItemCollection 类型里面的itemList 取出，添加到recyclerview item中，这样方便recyclerview处理
                recommend.itemList.forEach {
                    // ItemCollection
                    if (EyepetizerService.ItemDataType.ITEM_COLLECTION == EyepetizerService.ItemDataType.getItemDataType(
                            it.data.dataType
                        )
                    ) {
//                        Log.e(
//                            "RecommendPagingSource",
//                            "将squareCardCollection size: ${it.data.itemList.size}"
//                        )
                        value.addAll(it.data.itemList)
                    } else {
                        value.add(it)
                    }
                }
                val preKey = if (page > 0) page - 1 else null
                val nextKey = if (recommend.count > 0) page + 1 else null
                LoadResult.Page<Int, RecommendBean.Item>(value, preKey, nextKey)
            } catch (e: Exception) {
                e.printStackTrace()
                LoadResult.Error(e)
            }
        }
    }
}