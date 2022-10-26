package com.pp.module_home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSlideImageWithFooterViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding

class RecommendPagingDataAdapter2 :
    BindingPagingDataAdapter<ViewDataBinding, Any, Any>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {

            override fun areItemsTheSame(oldItem: Any, newItem: Any) =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any) =
                oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getItemViewType(item)
    }

    private val item_unknow = 0
    private val slide_cover_image_with_footer = item_unknow + 1
    private val feed_cover_large_video = slide_cover_image_with_footer + 1
    private val feed_cover_small_video = feed_cover_large_video + 1
    private fun getItemViewType(item: Any?): Int {
        return when (item) {
            is MetroLargeVideoCardItemViewModel -> feed_cover_large_video
            is MetroSmallVideoCardItemViewModel -> feed_cover_small_video
            is MetroSlideImageWithFooterViewModel -> slide_cover_image_with_footer
            else -> item_unknow
        }

    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            feed_cover_large_video ->
                ItemVideoCardBinding.inflate(layoutInflater, parent, false)

            feed_cover_small_video ->
                ItemVideoSmallCardBinding.inflate(layoutInflater, parent, false)

            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: Any?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            is ItemVideoCardBinding -> if (item is MetroLargeVideoCardItemViewModel) item else MetroLargeVideoCardItemViewModel(null)
            is ItemVideoSmallCardBinding -> if (item is MetroSmallVideoCardItemViewModel) item else MetroSmallVideoCardItemViewModel(null)
            // to be developed
            else -> {
                // todo: 待实现
                val data = item
                if (data is MetroSlideImageWithFooterViewModel) {
                    """
                        set banner list
                        size: ${data.metroList?.size}
                    """.trimIndent()
                } else {
                    "unknown"
                }
            }
        }
    }

}