package com.pp.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object DailyRepository {


    fun getPagingData(): Flow<PagingData<PageDataBean.Card.CardData.Body.Metro>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { DailyPagingSource() }).flow
    }

    private class DailyPagingSource : MetroPagingSource<PageDataBean.Card.CardData.Body.Metro>() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(page_label = "daily_issue", page_type = "card")
        }

        override fun getSetBannerList(card: PageDataBean.Card, metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<PageDataBean.Card.CardData.Body.Metro> {
            return mutableListOf()
        }

        override fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<PageDataBean.Card.CardData.Body.Metro> {
            val itemModels = mutableListOf<PageDataBean.Card.CardData.Body.Metro>()
            metroList?.forEach {
                // 只加载 video
                if (it.type == EyepetizerService2.MetroType.VIDEO) {
                    itemModels.add(it)
                }
            }
            return itemModels
        }

    }
}