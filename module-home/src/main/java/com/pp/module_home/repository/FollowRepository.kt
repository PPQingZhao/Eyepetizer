package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.ApiService
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object FollowRepository {
    /*fun getPagingData(): Flow<PagingData<Item>> {
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
    }*/

    fun getPagingData2(): Flow<PagingData<PageDataBean.Card.CardData.Body.Metro>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { FollowPagingSource2() }).flow
    }

    private class FollowPagingSource2 :
        PagingSource<String, PageDataBean.Card.CardData.Body.Metro>() {
        @ExperimentalPagingApi
        override fun getRefreshKey(state: PagingState<String, PageDataBean.Card.CardData.Body.Metro>): String? =
            null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, PageDataBean.Card.CardData.Body.Metro> {
            return try {
                val url = params.key ?: ApiService.URL_FOLLOW
                val followBean = ApiService.api.getFollow(url)

                val valueList = mutableListOf<PageDataBean.Card.CardData.Body.Metro>()
                var nextKey = ""
                followBean.result.cardList.forEach {
                    if (it.type == "") {
                        // TODO: 待实现
                        nextKey = it.cardData.body.apiRequest?.url.toString()
                    } else {
                        it.cardData.body.metroList?.forEach {
                            valueList.add(it)
                        }
                    }
                }

                val preKey = null
                LoadResult.Page<String, PageDataBean.Card.CardData.Body.Metro>(
                    valueList,
                    preKey,
                    nextKey
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}