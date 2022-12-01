package com.pp.library_common.pagingsource

import android.text.TextUtils
import android.util.Log
import androidx.paging.PagingSource
import com.pp.library_common.routerservice.RouterServices
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.PageDataBean

abstract class MetroPagingSource<Item : Any> :
    PagingSource<Key<Item>, Item>() {

    override suspend fun load(params: LoadParams<Key<Item>>): LoadResult<Key<Item>, Item> {
        return try {
            val valueList = mutableListOf<Item>()
            // 数据加载完毕
            if (params.key == null || params.key?.url == null) {
                return LoadResult.Page(valueList, null, null)
            }
            val nextKey = Key<Item>()
            if (params.key?.isLoadMore == true) {

                return params.key?.run {
                    nextKey.paramMap = this.paramMap
                    nextKey.url = this.url
                    nextKey.isLoadMore = true
                    nextKey.loadMore = loadMore
                    loadMore?.load(url, this.paramMap, nextKey)
                } ?: LoadResult.Page(mutableListOf(), null, null)

            } else {

                val response: BaseResponse<PageDataBean>? = loadPageData(params.key)

                response?.result?.cardList?.forEach {

                    when (it.type) {
                        EyepetizerService2.CardType.CALL_CARD_LIST -> {
                            // TODO: 加载更多  待研究改进
                            nextKey.url = it.cardData.body.apiRequest?.url

                            nextKey.paramMap = it.cardData.body.apiRequest?.params
                            nextKey.isLoadMore = true

                            // 加载更多实现  call_card_list
                            nextKey.loadMore = object : LoadMore<Item> {
                                override suspend fun load(
                                    url: String?,
                                    param: Map<String, String?>?,
                                    nextKey: Key<Item>
                                ): LoadResult.Page<Key<Item>, Item> {
                                    val data = mutableListOf<Item>()

                                    if (!TextUtils.isEmpty(url)) {
                                        val resultParam = param?.toMutableMap()
                                        resultParam?.putAll(extLoadMoreParams())
                                        val loadMoreBean =
                                            EyepetizerService2.api.getLoadMoreCardData(
                                                url!!,
                                                resultParam
                                            )

                                        loadMoreBean.result?.run {
                                            val toMutableMap = nextKey.paramMap?.toMutableMap()
                                            toMutableMap?.put("last_item_id", lastItemId.toString())
                                            nextKey.paramMap = toMutableMap
                                            // 数据加载完毕
                                            if (itemList == null || itemList.isEmpty()) {
                                                return LoadResult.Page(data, null, null)
                                            }

                                            itemList.forEach {
                                                data.addAll(
                                                    getLoadMoreMetroList(
                                                        it.cardData.body.metroList
                                                            ?: mutableListOf()
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    Log.e("TAG", nextKey.url ?: "")
                                    nextKey.paramMap?.forEach {
                                        Log.e("TAG", "key: ${it.key} value: ${it.value}")
                                    }
                                    Log.e("TAG", "call_card_list data: ${data.size}")
                                    return LoadResult.Page(data, null, nextKey)
                                }
                            }
                        }
                        EyepetizerService2.CardType.CALL_METRO_LIST -> {
                            // TODO: 加载更多  待研究改进
                            nextKey.url = it.cardData.body.apiRequest?.url

                            nextKey.paramMap = it.cardData.body.apiRequest?.params
                            nextKey.isLoadMore = true

                            // 加载更多实现  call_metro_list
                            nextKey.loadMore = object : LoadMore<Item> {
                                override suspend fun load(
                                    url: String?,
                                    param: Map<String, String?>?,
                                    nextKey: Key<Item>
                                ): LoadResult.Page<Key<Item>, Item> {

                                    val data = mutableListOf<Item>()
                                    if (!TextUtils.isEmpty(url)) {

                                        val resultParam = param?.toMutableMap()
                                        resultParam?.putAll(extLoadMoreParams())
                                        val loadMoreBean =
                                            EyepetizerService2.api.getLoadMoreMetroData(
                                                url!!,
                                                resultParam
                                            )

                                        loadMoreBean.result?.run {
                                            val toMutableMap = nextKey.paramMap?.toMutableMap()
                                            toMutableMap?.put("last_item_id", lastItemId.toString())
                                            nextKey.paramMap = toMutableMap
                                            // 数据加载完毕
                                            if (itemList == null || itemList.isEmpty()) {
                                                return LoadResult.Page(data, null, null)
                                            }
                                            data.addAll(getLoadMoreMetroList(itemList))
                                        }
                                    }

                                    Log.e("TAG", nextKey.url ?: "")
                                    nextKey.paramMap?.forEach {
                                        Log.e("TAG", "key: ${it.key} value: ${it.value}")
                                    }
                                    Log.e("TAG", "call_metro_list data: ${data.size}")
                                    return LoadResult.Page(data, null, nextKey)
                                }

                            }
/*
                            Log.e("TAG", nextKey.url ?: "")
                            nextKey.paramMap?.forEach {
                                Log.e("TAG", "key: ${it.key} value: ${it.value}")
                            }*/
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
    abstract fun getLoadMoreMetroList(itemList: List<Metro>): List<Item>

    /**
     * 创建 set banner list 类型
     */
    abstract fun getSetBannerList(
        card: Card,
        metroList: List<Metro>?
    ): List<Item>

    /**
     * 创建 set metro list 类型
     */
    abstract fun getSetMetroList(metroList: List<Metro>?): List<Item>

    /**
     * 加载 page
     */
    suspend fun loadPageData(key: Key<Item>?): BaseResponse<PageDataBean>? {
        return key?.run {
            val param = paramMap?.toMutableMap()
            param?.putAll(extPageParams())
            EyepetizerService2.api.getPageData2(url ?: "", param)
        }
    }

    protected open fun extPageParams(): Map<out String, String?> {
        return mutableMapOf("uid" to RouterServices.userService.getUid().toString())
    }

    protected open fun extLoadMoreParams(): Map<out String, String?> {
        return mutableMapOf("uid" to RouterServices.userService.getUid().toString())
    }

}

interface LoadMore<Item : Any> {
    suspend fun load(
        url: String?,
        param: Map<String, String?>?,
        nextKey: Key<Item>
    ): PagingSource.LoadResult.Page<Key<Item>, Item>
}

class Key<Item : Any> {
    var url: String? = null
    var paramMap: Map<String, String?>? = null
    var isLoadMore = false

    var loadMore: LoadMore<Item>? = null
}