package com.pp.module_details.repository

import androidx.paging.*
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.Style
import kotlinx.coroutines.flow.Flow

object RelatedRecommendRepository {
    fun getPagingData(
        resourceId: Long?,
        resourceType: String?,
    ): Flow<PagingData<Metro>> {
        return Pager(
            initialKey = Param(resourceId, resourceType),
            config = PagingConfig(10),
            pagingSourceFactory = {
                CommentsPagingSource()
            }).flow
    }

    private class Param(
        val resourceId: Long? = null,
        val resourceType: String? = null,
        val last_item_id: Int? = null
    )

    private class CommentsPagingSource() :
        PagingSource<Param, Metro>() {
        val NO_DATA = -1

        override suspend fun load(params: LoadParams<Param>): LoadResult<Param, Metro> {
            return try {
                val key = params.key
                if (null == key || key.last_item_id == NO_DATA) {
                    return LoadResult.Page<Param, Metro>(
                        mutableListOf(),
                        null,
                        null
                    )
                }

//                val responseBody = EyepetizerService2.itemApi.getRelatedRecommend2(
//                    key.resourceId,
//                    key.resourceType,
//                )
//
//                val string = responseBody.string()
//                Log.e("TAG", string)

                val response = EyepetizerService2.itemApi.getRelatedRecommend(
                    key.resourceId,
                    key.resourceType,
                )

                val recommendBean = response.result

                val valueList = mutableListOf<Metro>()
                recommendBean?.itemList?.onEach {
                    valueList.add(
                        Metro(
                            metroData = it,
                            style = Style(tplLabel = EyepetizerService2.MetroType.Style.feed_cover_large_video)
                        )
                    )
                }

                val nextKey = Param(
                    key.resourceId,
                    key.resourceType
                )

                LoadResult.Page<Param, Metro>(
                    valueList,
                    null,
                    null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}