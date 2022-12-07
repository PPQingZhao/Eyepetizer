package com.pp.module_discovery.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_network.eyepetizer.EyepetizerService2
import kotlinx.coroutines.flow.Flow

object DiscoveryRepository {

    fun getPagingData(
    ): Flow<PagingData<Any>> {
        val params = mutableMapOf(
            "page_label" to "discover_v2",
            "page_type" to "card"
        )
        return Pager<Map<String,String>, Any>(PagingConfig(10),
            initialKey = params,
            pagingSourceFactory = { MultiTypePagingSource() }).flow
    }

    private class MultiTypePagingSource :
        PagingSource<Map<String,String>, Any>() {
        override suspend fun load(params: LoadParams<Map<String,String>>): LoadResult<Map<String,String>, Any> {
            return try {
                val key = params.key ?: return LoadResult.Page(mutableListOf(), null, null)

                val response = key.run {
                    EyepetizerService2.api.getPageData(key)
                }

                val valueList = mutableListOf<Any>()
                response.result?.cardList?.forEach { card ->
                    if (card.cardData.header.left.isNotEmpty()
                        || card.cardData.header.right.isNotEmpty()
                        || card.cardData.header.center.isNotEmpty()
                    ) {
                        valueList.add(card.cardData.header)
                    }
                    when (card.type) {
                        EyepetizerService2.CardType.SET_METRO_LIST -> {

                            card.cardData.body.metroList?.forEach {
                                when (it.style.tplLabel) {
                                    EyepetizerService2.MetroType.Style.default_web,
                                    EyepetizerService2.MetroType.Style.stacked_slide_cover_image,
                                    EyepetizerService2.MetroType.Style.icon_grid -> {
                                        valueList.add(it)
                                    }
                                }

                            }
                        }
                        EyepetizerService2.CardType.SET_SLIDE_METRO_LIST -> {
                            valueList.add(card)
                        }
                    }
                }

                LoadResult.Page(valueList, null, null)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}