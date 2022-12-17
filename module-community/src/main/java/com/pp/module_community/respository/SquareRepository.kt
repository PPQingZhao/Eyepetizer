package com.pp.module_community.respository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.extension.isVideo
import com.pp.library_common.model.MultiItemEntity
import com.pp.library_common.pagingsource.Key
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.LoadMoreBean
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.module_community.model.SquareBannerListViewModel
import com.pp.module_community.model.SquareImageLargeItemViewModel
import com.pp.module_community.model.SquareVideoLargeItemViewModel
import com.pp.module_community.model.SquareVideoSmallItemViewModel
import kotlinx.coroutines.flow.Flow

object SquareRepository {

    private const val TAG = "SquareRepository"

    fun getPagingData(): Flow<PagingData<MultiItemEntity>> {
        val key = Key<MultiItemEntity>()
        key.url = EyepetizerService2.BASE_URL_GET_PAGE
        key.paramMap = mutableMapOf(
            "page_label" to "community",
            "page_type" to "card"
        )
        return Pager(
            initialKey = key,
            config = PagingConfig(15),
            pagingSourceFactory = { SquarePagingSource() }).flow
    }

    private class SquarePagingSource :
        MetroPagingSource<MultiItemEntity>() {
        override fun getSetBannerList(
            card: Card,
            metroList: List<Metro>?,
        ): List<MultiItemEntity> {
            val bannerList = mutableListOf<SquareBannerListViewModel>()
            metroList?.let {
                bannerList.add(SquareBannerListViewModel(card, it))
            }
            Log.e(TAG, "bannerList size：${bannerList.size}")
            return bannerList
        }

        override fun getSetMetroList(metroList: List<Metro>?): List<MultiItemEntity> {
            val itemModels = mutableListOf<MultiItemEntity>()
            metroList?.forEach { metro ->
                val style = metro.style.tplLabel
                when (style) {
                    EyepetizerService2.MetroType.Style.feed_cover_small_video,
                    EyepetizerService2.MetroType.Style.waterfall_cover_small_image,
                    EyepetizerService2.MetroType.Style.waterfall_cover_small_video,
                    -> {
                        itemModels.add(SquareVideoSmallItemViewModel(metro))
                    }
                    EyepetizerService2.MetroType.Style.feed_item_detail -> {
                        if (metro.metroData?.isVideo() != false) {
                            itemModels.add(SquareVideoLargeItemViewModel(metro))
                        } else {
                            itemModels.add(SquareImageLargeItemViewModel(metro))
                        }
                    }
                    EyepetizerService2.MetroType.Style.feed_cover_large_video -> {
                        itemModels.add(SquareVideoLargeItemViewModel(metro))
                    }
                    EyepetizerService2.MetroType.Style.feed_cover_large_image -> {
                        itemModels.add(SquareImageLargeItemViewModel(metro))
                    }
                    else -> {
                        Log.e(TAG, "待开发类型：$style")
                    }
                }
            }
            Log.e(TAG, "SetMetroList size：${itemModels.size}")
            return itemModels
        }

        override fun getLoadMoreMetroList(itemList: List<Metro>): List<MultiItemEntity> {
            return getSetMetroList(itemList)
        }

        private var count = 10
        override fun isLoadMoreCardDataEnd(loadMoreBean: BaseResponse<LoadMoreBean<Card>>): Boolean {
            return (--count < 0) || super.isLoadMoreCardDataEnd(loadMoreBean)
        }
    }
}