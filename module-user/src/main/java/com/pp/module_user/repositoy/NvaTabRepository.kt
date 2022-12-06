package com.pp.module_user.repositoy

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Metro
import kotlinx.coroutines.flow.Flow


object NvaTabRepository {
    fun getPagingData(
        uid: Int,
        pageType: String,
        pageLabel: String
    ): Flow<PagingData<Metro>> {
        return Pager<Param, Metro>(PagingConfig(10),
            initialKey = Param(uid, pageType, pageLabel),
            pagingSourceFactory = { NvaTabPagingSource() }).flow
    }

    private data class Param(
        val uid: Int,
        val pageType: String,
        val pageLabel: String,
    )

    private class NvaTabPagingSource :
        PagingSource<Param, Metro>() {
        override suspend fun load(params: LoadParams<Param>): LoadResult<Param, Metro> {
            return try {
                val key = params.key ?: return LoadResult.Page(mutableListOf(), null, null)

                val response = key.run {
                    EyepetizerService2.api.getPageData(uid, pageType, pageLabel)
                }

                val dataList = response.result?.run {
                    val list = mutableListOf<Metro>()
                    cardList.forEach {
                        it.cardData.body.metroList?.forEach {
                            list.add(it)
                        }
                    }

                    list
                } ?: mutableListOf()

                LoadResult.Page(dataList, null, null)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

}