package com.pp.module_comments.repository

import androidx.annotation.ColorInt
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_base.adapter.TreeNodeAdapter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.BaseResponse
import com.pp.library_network.eyepetizer.bean.Comment
import com.pp.library_network.eyepetizer.bean.LoadMoreBean
import com.pp.library_ui.adapter.TreeNode
import com.pp.module_comments.model.CommentItemViewModel
import kotlinx.coroutines.flow.Flow

object CommentRepository {

    fun getPagingData(
        resourceId: Long?,
        resourceType: String?,
        sort_type: String,
        @ColorInt color: Int,
        refresh: () -> String?,
    ): Flow<PagingData<TreeNode>> {
        return Pager(
            initialKey = Param(
                resourceId,
                resourceType,
                sort_type
            ),
            config = PagingConfig(15, prefetchDistance = 1),
            pagingSourceFactory = {
                CommentsPagingSource(color,refresh)
            }).flow
    }

    private class Param(
        val resourceId: Long? = null,
        val resourceType: String? = null,
        val sort_type: String? = null,
        val last_item_id: Int? = null
    )

    private class CommentsPagingSource(@ColorInt val color: Int, val refresh: () -> String?) :
        PagingSource<Param, TreeNode>() {

        override suspend fun load(params: LoadParams<Param>): LoadResult<Param, TreeNode> {
            return try {
                val key = params.key ?: return LoadResult.Page(mutableListOf(), null, null)

                val response: BaseResponse<LoadMoreBean<Comment>> =
                    EyepetizerService2.itemApi.getCMSCommentList(
                        key.resourceId,
                        key.resourceType,
                        key.sort_type,
                        key.last_item_id
                    )

                val commentsBean = response.result
                val commentList = mutableListOf<CommentItemViewModel>()
                commentsBean?.itemList?.onEach {
                    commentList.add(CommentItemViewModel(it, color))
                }

                val valueList = mutableListOf<TreeNode>()
                commentList.onEach {
                    valueList.addAll(TreeNodeAdapter.expandNode(it))
                }

                var nextKey: Param? = null
                val preKey = null
                if ((commentsBean?.lastItemId ?: -1) > (key.last_item_id ?: 0)) {
                    nextKey = Param(
                        key.resourceId,
                        key.resourceType,
                        key.sort_type,
                        commentsBean?.lastItemId
                    )
                }

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