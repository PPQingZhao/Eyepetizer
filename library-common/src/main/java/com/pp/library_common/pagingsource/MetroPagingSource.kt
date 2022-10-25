package com.pp.library_common.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean

abstract class MetroPagingSource :
    PagingSource<String, ItemModel<Any>>() {
    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<String, ItemModel<Any>>): String? =
        null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ItemModel<Any>> {
        return try {

            val response: BaseResponse<PageDataBean> = loadPageData(params.key)

            val valueList = mutableListOf<ItemModel<Any>>()
            var nextKey = ""
            response.result.cardList.forEach {
                when (it.type) {
                    EyepetizerService2.CardType.CALL_METRO_LIST -> {
                        // TODO: 待实现
                        nextKey = it.cardData.body.apiRequest?.url.toString()
                    }

                    EyepetizerService2.CardType.SET_BANNER_LIST -> {
                        val metroList = mutableListOf<PageDataBean.Card.CardData.Body.Metro>()
                        it.cardData.body.metroList?.forEach {
                            metroList.add(it)
                        }
                        valueList.add(ItemModel(metroList))
                    }

                    EyepetizerService2.CardType.SET_METRO_LIST -> {
                        it.cardData.body.metroList?.forEach {
                            valueList.add(ItemModel(it))
                        }
                    }
                }
            }

            val preKey = null
            LoadResult.Page<String, ItemModel<Any>>(
                valueList,
                preKey,
                nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun loadPageData(key: String?): BaseResponse<PageDataBean>
}