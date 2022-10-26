package com.pp.module_home.repository

import androidx.paging.*
import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import kotlinx.coroutines.flow.Flow

object FollowRepository {

    fun getPagingData2(): Flow<PagingData<MetroFollowItemViewModel>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { FollowPagingSource() }).flow
    }

    private class FollowPagingSource : MetroPagingSource<MetroFollowItemViewModel>() {
        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(key ?: EyepetizerService2.URL_DAILY)
        }

        override fun getSetBannerList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<MetroFollowItemViewModel> {
            return mutableListOf()
        }

        override fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<MetroFollowItemViewModel> {
            val itemModels = mutableListOf<MetroFollowItemViewModel>()
            metroList?.forEach {
                // 只加载 video
                if (it.type == EyepetizerService2.MetroType.VIDEO) {
                    itemModels.add(MetroFollowItemViewModel(it))
                }
            }

            return itemModels
        }

    }

}