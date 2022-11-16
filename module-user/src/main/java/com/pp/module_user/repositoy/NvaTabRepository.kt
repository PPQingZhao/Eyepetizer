package com.pp.module_user.repositoy

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow


object NvaTabRepository {
    fun getPagingData(
        uid: Int,
        pageType: String,
        pageLabel: String
    ): Flow<PagingData<PageDataBean.Card.CardData.Body.Metro>> {
        return Pager<Param, PageDataBean.Card.CardData.Body.Metro>(PagingConfig(10),
            initialKey = Param(uid, pageType, pageLabel, false),
            pagingSourceFactory = { NvaTabPagingSource() }).flow
    }

    private data class Param(
        val uid: Int,
        val pageType: String,
        val pageLabel: String,
        val end: Boolean
    )

    private class NvaTabPagingSource :
        PagingSource<Param, PageDataBean.Card.CardData.Body.Metro>() {
        override suspend fun load(params: LoadParams<Param>): LoadResult<Param, PageDataBean.Card.CardData.Body.Metro> {
            return try {
                val key = params.key
                if (key?.end == true) {
                    return LoadResult.Page(mutableListOf(), null, null)
                }

                val response = key?.run {
                    EyepetizerService2.api.getPageData(uid, pageType, pageLabel)
                }

                val dataList = response?.result?.run {
                    val list = mutableListOf<PageDataBean.Card.CardData.Body.Metro>()
                    cardList.forEach {
                        it.cardData.body.metroList?.forEach {
                            list.add(it)
                        }
                    }

                    list
                } ?: mutableListOf()

                LoadResult.Page(dataList, null, Param(-1, "", "", true))
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

}