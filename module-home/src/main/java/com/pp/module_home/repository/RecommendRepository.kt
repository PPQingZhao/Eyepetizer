package com.pp.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSlideImageWithFooterViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object RecommendRepository {

    fun getPagingData(): Flow<PagingData<Any>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { RecommendPagingSource() }).flow
    }

    private class RecommendPagingSource : MetroPagingSource<Any>() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(key ?: EyepetizerService2.URL_RECOMMEND)
        }

        override fun getSetBannerList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<Any> {
            val itemModels = mutableListOf<Any>()
            itemModels.add(MetroSlideImageWithFooterViewModel(metroList))
            return itemModels
        }

        override fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<Any> {
            val itemModels = mutableListOf<Any>()
            metroList?.forEach {

                if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video) {
                    itemModels.add(MetroLargeVideoCardItemViewModel(it))
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_small_video) {
                    itemModels.add(MetroSmallVideoCardItemViewModel(it))
                }
            }

            return itemModels
        }

    }


}