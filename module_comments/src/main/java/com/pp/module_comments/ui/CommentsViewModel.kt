package com.pp.module_comments.ui

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_ui.R
import com.pp.library_ui.adapter.TreeNode
import com.pp.module_comments.repository.CommentRepository
import com.pp.mvvm.LifecycleViewModel
import kotlinx.coroutines.flow.Flow

class CommentsViewModel(app: Application) : LifecycleViewModel(app) {

    val title = ObservableField<Int>(R.string.comment_hot)
    val comment_sort_type = ObservableField<Int>(com.pp.library_ui.R.string.sort_type_hot)
    var sort_type = MutableLiveData<String>(EyepetizerService2.SortType.SORT_TYPE_HOT)


    /**
     * 获取评论
     */
    fun getPageData(
        resourceId: Int?,
        resourceType: String?,
        sort_type: String
    ): Flow<PagingData<TreeNode>> {
        return CommentRepository.getPagingData(
            resourceId,
            resourceType,
            sort_type
        ) { this.sort_type.value }
    }


    /**
     * 评论排序点击事件
     */
    fun onCommentType(view: View) {
        // 更新 sort_type
        sort_type.value = if (sort_type.value == EyepetizerService2.SortType.SORT_TYPE_HOT)
            EyepetizerService2.SortType.SORT_TYPE_TIME else EyepetizerService2.SortType.SORT_TYPE_HOT

        // 更新
        if (sort_type.value == EyepetizerService2.SortType.SORT_TYPE_HOT) {
            title.set(R.string.comment_hot)
            comment_sort_type.set(R.string.sort_type_hot)
        } else {
            title.set(R.string.comment_new)
            comment_sort_type.set(R.string.sort_type_time)
        }
    }


}
