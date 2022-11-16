package com.pp.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.model.ItemModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import kotlinx.coroutines.flow.Flow

object RecommendRepository {

    fun getPagingData(): Flow<PagingData<Any>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecommendPagingSource() }).flow
    }

    private class RecommendPagingSource : MetroPagingSource<Any>() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(page_type = "card", page_label = "recommend")
        }

        override fun getSetBannerList(card: PageDataBean.Card, metroList: List<Metro>?): List<Any> {
            val itemModels = mutableListOf<Any>()
            itemModels.add(ItemModel(EyepetizerService2.CardType.SET_BANNER_LIST, card))
            return itemModels
        }

        override fun getSetMetroList(metroList: List<Metro>?): List<Any> {
            val itemModels = mutableListOf<Metro>()
            metroList?.forEach {

                if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video) {
                    itemModels.add(it)
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_small_video) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

    }


}