package com.pp.library_common.pagingsource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean

abstract class MetroPagingSource<Item : Any> :
    PagingSource<String, Item>() {
    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<String, Item>): String? =
        null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        return try {

            val response: BaseResponse<PageDataBean> = loadPageData(params.key)

            val valueList = mutableListOf<Item>()
            var nextKey = ""
            response.result?.cardList?.forEach {
                when (it.type) {
                    EyepetizerService2.CardType.CALL_METRO_LIST -> {
                        // TODO: 待实现
                        nextKey = it.cardData.body.apiRequest?.url.toString()
                    }

                    EyepetizerService2.CardType.SET_BANNER_LIST -> {
                        valueList.addAll(getSetBannerList(it, it.cardData.body.metroList))
                    }

                    EyepetizerService2.CardType.SET_METRO_LIST -> {
                        valueList.addAll(getSetMetroList(it.cardData.body.metroList))
                    }
                }
            }

            Log.e("TAG","data: ${valueList.size}")
            val preKey = null
            LoadResult.Page<String, Item>(
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
     * 创建 set banner list 类型
     */
    abstract fun getSetBannerList(card: PageDataBean.Card, metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<Item>

    /**
     * 创建 set metro list 类型
     */
    abstract fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<Item>

    /**
     * 加载 page data
     */
    abstract suspend fun loadPageData(key: String?): BaseResponse<PageDataBean>
}