package com.pp.library_common.pagingsource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pp.library_network.eyepetizer.EyepetizerApi
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.LoadMorePageBean
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.PageDataBean

abstract class MetroPagingSource<Item : Any> :
    PagingSource<Key, Item>() {

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Item> {
        return try {

            val valueList = mutableListOf<Item>()
            // 数据加载完毕
            if(params.key == null || params.key?.url ==null){
                return LoadResult.Page(valueList,null,null)
            }
            val nextKey = Key()
            if (params.key?.loadMore == true) {
                val response = loadMorePageData(key = params.key)
                response?.result?.run {
                    // 数据加载完毕
                    if(itemList == null || itemList.isEmpty()){
                        return LoadResult.Page(valueList,null,null)
                    }

                    val paramMap = params.key?.params?.toMutableMap()
                    paramMap?.put("last_item_id", lastItemId.toString())
                    nextKey.params = paramMap
                    nextKey.url = params.key?.url
                    nextKey.loadMore = true

                    valueList.addAll(getLoadMoreList(itemList))
                }
            } else {
                val response: BaseResponse<PageDataBean>? = loadPageData(params.key)

                response?.result?.cardList?.forEach {

                    when (it.type) {
                        EyepetizerService2.CardType.CALL_METRO_LIST -> {
                            // TODO: 加载更多  待研究改进
                            nextKey.url = it.cardData.body.apiRequest?.url
                            nextKey.params = it.cardData.body.apiRequest?.params
                            nextKey.loadMore = true
                            Log.e("TAG", nextKey.url ?: "")
                        }

                        EyepetizerService2.CardType.SET_BANNER_LIST -> {
                            valueList.addAll(getSetBannerList(it, it.cardData.body.metroList))
                        }

                        EyepetizerService2.CardType.SET_WATERFALL_METRO_LIST,
                        EyepetizerService2.CardType.SET_METRO_LIST -> {
                            valueList.addAll(getSetMetroList(it.cardData.body.metroList))
                        }
                    }
                }

            }
            Log.e("TAG", "data: ${valueList.size}")
            val preKey = null
            LoadResult.Page(
                valueList,
                preKey,
                nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    /**
     * 加载更多数据处理
     */
    abstract fun getLoadMoreList(itemList: List<Metro>): List<Item>

    /**
     * 创建 set banner list 类型
     */
    abstract fun getSetBannerList(
        card: PageDataBean.Card,
        metroList: List<Metro>?
    ): List<Item>

    /**
     * 创建 set metro list 类型
     */
    abstract fun getSetMetroList(metroList: List<Metro>?): List<Item>

    /**
     * 加载 更多
     */
    suspend fun loadMorePageData(key: Key?): BaseResponse<LoadMorePageBean>? {
        return key?.run {
            EyepetizerService2.api
                .getLoadMorePageData(url ?: "",params)
        }
    }

    /**
     * 加载 page
     */
    suspend fun loadPageData(key: Key?): BaseResponse<PageDataBean>? {
        return key?.run {
            EyepetizerService2.api
                .getPageData2(url ?: "",params)
        }
    }
}

class Key {
    var url: String? = null
    var params: Map<String, String>? = null
    var loadMore = false
}