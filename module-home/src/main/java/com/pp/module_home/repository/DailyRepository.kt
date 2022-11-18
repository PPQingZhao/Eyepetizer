package com.pp.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.pagingsource.Key
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import kotlinx.coroutines.flow.Flow

object DailyRepository {


    fun getPagingData(): Flow<PagingData<Metro>> {
        val key = Key<Metro>()
        key.url = EyepetizerService2.BASE_URL_GET_PAGE
        key.paramMap = mutableMapOf(
            "page_label" to "daily_issue",
            "page_type" to "card"
        )
        return Pager(
            initialKey = key,
            config = PagingConfig(15),
            pagingSourceFactory = { DailyPagingSource() }).flow
    }

    private class DailyPagingSource : MetroPagingSource<Metro>() {

        override fun getSetBannerList(card: Card, metroList: List<Metro>?): List<Metro> {
            return mutableListOf()
        }

        override fun getSetMetroList(metroList: List<Metro>?): List<Metro> {
            val itemModels = mutableListOf<Metro>()
            metroList?.forEach {
                // 只加载 video
                if (it.type == EyepetizerService2.MetroType.VIDEO) {
                    itemModels.add(it)
                }
            }
            return itemModels
        }

        override fun getLoadMoreMetroList(itemList: List<Metro>): List<Metro> {
            val itemModels = mutableListOf<Metro>()
            itemList?.forEach {
                // 只加载 video
                if (it.type == EyepetizerService2.MetroType.VIDEO) {
                    itemModels.add(it)
                }
            }
            return itemModels
        }

    }
}