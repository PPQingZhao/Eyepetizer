package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_common.pagingsource.ItemModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object RecommendRepository {
    /*fun getPagingData(): Flow<PagingData<RecommendBean.Item>> {
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
                Log.e("TAG","size: ${recommend.itemList.size}")
                // 遍历: 将 ItemCollection 类型里面的itemList 取出，添加到recyclerview item中，这样方便recyclerview处理
                recommend.itemList.forEach {

                    // ItemCollection
                    if (EyepetizerService.ItemDataType.ITEM_COLLECTION == EyepetizerService.ItemDataType.getItemDataType(it.data.dataType)) {
//                        Log.e("RecommendPagingSource", "将squareCardCollection size: ${it.data.itemList.size}")
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
    }*/

    fun getPagingData(): Flow<PagingData<ItemModel<Any>>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecommendPagingSource() }).flow
    }

    private class RecommendPagingSource : MetroPagingSource() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(key ?: EyepetizerService2.URL_RECOMMEND)
        }

    }


}