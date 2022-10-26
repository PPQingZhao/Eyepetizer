package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object DailyRepository {


    fun getPagingData(): Flow<PagingData<MetroLargeVideoCardItemViewModel>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { DailyPagingSource() }).flow
    }

    private class DailyPagingSource : MetroPagingSource<MetroLargeVideoCardItemViewModel>() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(key ?: EyepetizerService2.URL_DAILY)
        }

        override fun getSetBannerList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<MetroLargeVideoCardItemViewModel> {
            return mutableListOf()
        }

        override fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<MetroLargeVideoCardItemViewModel> {
            val itemModels = mutableListOf<MetroLargeVideoCardItemViewModel>()
            metroList?.forEach {
                // 只加载 video
                if (it.type == EyepetizerService2.MetroType.VIDEO) {
                    itemModels.add(MetroLargeVideoCardItemViewModel(it))
                }
            }
            return itemModels
        }

    }
}