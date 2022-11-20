package com.pp.module_home.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pp.library_common.pagingsource.Key
import com.pp.library_common.pagingsource.MetroPagingSource
import com.pp.library_common.routerservice.RouterServices
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Card
import com.pp.library_network.eyepetizer.bean.Metro
import kotlinx.coroutines.flow.Flow

object FollowRepository {

    fun getPagingData(): Flow<PagingData<Metro>> {
        val key = Key<Metro>()
        key.url = EyepetizerService2.BASE_URL_GET_PAGE
        key.paramMap = mutableMapOf(
            "page_label" to "follow",
            "page_type" to "card"
        )
        return Pager(
            initialKey = key,
            config = PagingConfig(15),
            pagingSourceFactory = { FollowPagingSource() }).flow
    }

    private class FollowPagingSource : MetroPagingSource<Metro>() {

        override fun getSetBannerList(card: Card, metroList: List<Metro>?): List<Metro> {
            return mutableListOf()
        }

        override fun getSetMetroList(metroList: List<Metro>?): List<Metro> {
            val itemModels = mutableListOf<Metro>()
            metroList?.forEach {
                Log.e("TAG", "type: ${it.type}  style typ: ${it.style.tplLabel}")
                if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_item_detail) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

        override fun getLoadMoreMetroList(itemList: List<Metro>): List<Metro> {
            val itemModels = mutableListOf<Metro>()
            itemList?.forEach {
//                Log.e("TAG","type: ${it.type}  style typ: ${it.style.tplLabel}")
                if (it.style.tplLabel == EyepetizerService2.MetroType.Style.feed_item_detail) {
                    itemModels.add(it)
                }
            }

            return itemModels
        }

    }

}