package com.pp.module_comments.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_base.adapter.TreeNodeAdapter
import com.pp.library_common.app.App
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.CommentsBean
import com.pp.library_ui.R
import com.pp.library_ui.adapter.TreeNode
import com.pp.module_comments.model.CommentItemViewModel
import kotlinx.coroutines.flow.Flow

object CommentRepository {
    fun getPagingData(
        resourceId: Int?,
        resourceType: String?,
        sort_type: String
    ): Flow<PagingData<TreeNode>> {
        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = {
                FollowPagingSource(
                    Param(
                        resourceId,
                        resourceType,
                        sort_type
                    )
                )
            }).flow
    }

    private class Param(
        val resourceId: Int? = null,
        val resourceType: String? = null,
        val sort_type: String? = null,
        val last_item_id: Int? = null
    )

    private class FollowPagingSource(val startParam: Param) : PagingSource<Param, TreeNode>() {
        val NO_DATA = -1
        override suspend fun load(params: LoadParams<Param>): LoadResult<Param, TreeNode> {
            return try {
                val key = params.key ?: startParam

                if (key.last_item_id == NO_DATA) {
                    return LoadResult.Page<Param, TreeNode>(
                        mutableListOf(),
                        null,
                        null
                    )
                }

                val response: BaseResponse<CommentsBean> = EyepetizerService2.api.getCMSCommentList(
                    key.resourceId,
                    key.resourceType,
                    key.sort_type,
                    key.last_item_id
                )

                val commentsBean = response.result
                val commentList = mutableListOf<CommentItemViewModel>()
                commentsBean.itemList.onEach {
                    commentList.add(
                        CommentItemViewModel(
                            it, App.getInstance()
                                .resources
                                .getColor(R.color.mediaTextColorSecondary)
                        )
                    )
                }

                val valueList = mutableListOf<TreeNode>()
                commentList.onEach {
                    valueList.addAll(TreeNodeAdapter.expandNode(it))
                }

               /* Log.e(
                    "TAG",
                    "lastItemId: ${commentsBean.lastItemId} pageCount: ${commentsBean.pageCount} itemCount: ${commentsBean.itemCount} itemPerPage: ${commentsBean.itemPerPage}"
                )*/
                val last_item_id = if (commentsBean.lastItemId > (startParam.last_item_id ?: 0))
                    commentsBean.lastItemId else NO_DATA

                val nextKey = Param(
                    key.resourceId,
                    key.resourceType,
                    key.sort_type,
                    last_item_id
                )

                val preKey = null
                LoadResult.Page<Param, TreeNode>(
                    valueList,
                    preKey,
                    nextKey
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

}