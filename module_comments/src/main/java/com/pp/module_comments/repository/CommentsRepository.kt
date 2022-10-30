package com.pp.module_comments.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.pp.library_base.adapter.TreeNodeAdapter
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_ui.adapter.TreeNode
import com.pp.module_comments.model.CommentItemViewModel
import kotlinx.coroutines.flow.Flow

object CommentsRepository {
    fun getPagingData(
        resource_id: Int?,
        resource_type: String?,
        sort_type: String?
    ): Flow<PagingData<TreeNode>> {

        return Pager(
            config = PagingConfig(15),
            pagingSourceFactory = {
                CommentsPagingSource(
                    resource_id,
                    resource_type,
                    sort_type
                )
            }).flow
    }

    private class CommentsPagingSource(
        val resource_id: Int?,
        val resource_type: String?,
        val sort_type: String?
    ) : PagingSource<String, TreeNode>() {
        override suspend fun load(params: LoadParams<String>): LoadResult<String, TreeNode> {
            return try {

                val response =
                    EyepetizerService2.api.getCMSCommentList(resource_id, resource_type, sort_type)

                val commentList = mutableListOf<CommentItemViewModel>()
                response.result.itemList.onEach {
                    commentList.add(CommentItemViewModel(it))
                }


                val valueList = mutableListOf<TreeNode>()
                commentList.onEach {
                    valueList.addAll(TreeNodeAdapter.expandNode(it))
                }

                Log.e("TAG","data count: ${valueList.size}")
                val preKey = null
                val nextKey = null
                LoadResult.Page<String, TreeNode>(
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