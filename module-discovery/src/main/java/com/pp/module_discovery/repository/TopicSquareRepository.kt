package com.pp.module_discovery.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.model.ItemModel
import com.pp.library_common.pagingsource.Key
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.nav.NavDataBean
import kotlinx.coroutines.flow.Flow

object TopicSquareRepository {

    const val TAG = "TopicListRepository"

    suspend fun getTopicNav(type: String): BaseResponse<NavDataBean> {

        val map = mutableMapOf<String, String>()
        map["tab_label"] = type

        return EyepetizerService2.api.getNavData(EyepetizerService2.BASE_URL_GET_NAV, map)
    }

    fun getPagingData(pageLabel: String, pageType: String): Flow<PagingData<Any>> {
        val map = HashMap<String, String>()
        map["page_type"] = pageType
        map["page_label"] = pageLabel
        val key = Key<Any>()
        key.url = EyepetizerService2.BASE_URL_GET_PAGE
        key.paramMap = map

        return Pager(
            initialKey = key,
            config = PagingConfig(15),
            pagingSourceFactory = { TopicPagingSource() }).flow
    }

    private class TopicPagingSource : MetroPagingSource<Any>() {

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
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_detail_topic) {
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
                } else if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_detail_topic) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

    }
}