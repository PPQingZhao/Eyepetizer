package com.pp.module_community.respository

import android.util.Log
import androidx.paging.*
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.module_community.model.SquareBannerListViewModel
import com.pp.library_common.model.MultiItemEntity
import com.pp.module_community.model.SquareVideoLargeItemViewModel
import com.pp.module_community.model.SquareVideoSmallItemViewModel
import kotlinx.coroutines.flow.Flow

object SquareRepository {

    private const val TAG = "SquareRepository"

    fun getPagingData(): Flow<PagingData<MultiItemEntity>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = { SquarePagingSource() }).flow
    }

    private class SquarePagingSource :
        MetroPagingSource<MultiItemEntity>() {
        override fun getSetBannerList(
            card: PageDataBean.Card,
            metroList: List<PageDataBean.Card.CardData.Body.Metro>?
        ): List<MultiItemEntity> {
            val bannerList = mutableListOf<SquareBannerListViewModel>()
            metroList?.let {
                bannerList.add(SquareBannerListViewModel(card, it))
            }
            return bannerList
        }

        override fun getSetMetroList(metroList: List<PageDataBean.Card.CardData.Body.Metro>?): List<MultiItemEntity> {
            val itemModels = mutableListOf<MultiItemEntity>()
            metroList?.forEach { metro ->
                val style = metro.style.tplLabel
                when (style) {
                    EyepetizerService2.MetroType.Style.feed_cover_small_video,
                    EyepetizerService2.MetroType.Style.waterfall_cover_small_video -> {
                        itemModels.add(SquareVideoSmallItemViewModel(metro))
                    }
                    EyepetizerService2.MetroType.Style.feed_item_detail,
                    EyepetizerService2.MetroType.Style.feed_cover_large_video,
                    EyepetizerService2.MetroType.Style.feed_cover_large_image -> {
                        itemModels.add(SquareVideoLargeItemViewModel(metro))
                    }
                    else -> {
                        Log.e(TAG, "待开发类型：$style")
                    }
                }
            }
            return itemModels
        }

        override suspend fun loadPageData(key: String?): BaseResponse<PageDataBean> {
            return EyepetizerService2.api.getPageData(page_label = "community", page_type = "card")
        }

    }
}