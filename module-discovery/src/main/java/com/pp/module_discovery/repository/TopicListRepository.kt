package com.pp.module_discovery.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.model.ItemModel
import com.pp.library_common.pagingsource.Key
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.ApiRequest
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import kotlinx.coroutines.flow.Flow

object TopicListRepository {

    const val TAG = "TopicListRepository"

    fun getTopicList(apiRequest: ApiRequest): Flow<PagingData<Any>> {
        val key = Key<Any>()
        key.url = apiRequest.url
        key.paramMap = apiRequest.params
        return Pager(
            initialKey = key,
            config = PagingConfig(15),
            pagingSourceFactory = { TopicListPagingSource() }).flow
    }

    private class TopicListPagingSource : MetroPagingSource<Any>() {

        override fun getSetBannerList(card: Card, metroList: List<Metro>?): List<Any> {
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
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.description_text) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

        override fun getLoadMoreMetroList(itemList: List<Metro>): List<Any> {
            val itemModels = mutableListOf<Metro>()
            itemList.forEach {

                if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video) {
                    itemModels.add(it)
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_small_video) {
                    itemModels.add(it)
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.description_text) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

    }
}