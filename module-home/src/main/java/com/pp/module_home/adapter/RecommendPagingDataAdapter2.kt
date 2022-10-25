package com.pp.module_home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_common.model.MetroLargeVideoCardItemViewModel
import com.pp.library_common.model.MetroSmallVideoCardItemViewModel
import com.pp.library_common.pagingsource.ItemModel
import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_ui.databinding.ItemVideoCardBinding
import com.pp.library_ui.databinding.ItemVideoSmallCardBinding

class RecommendPagingDataAdapter2 :
    BindingPagingDataAdapter<ViewDataBinding, Any, ItemModel<Any>>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemModel<Any>>() {

            override fun areItemsTheSame(oldItem: ItemModel<Any>, newItem: ItemModel<Any>) =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ItemModel<Any>, newItem: ItemModel<Any>) =
                oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getFollowItemType(item)
    }

    private val item_unknow = 0
    private val slide_cover_image_with_footer = item_unknow + 1
    private val feed_cover_large_video = slide_cover_image_with_footer + 1
    private val feed_cover_small_video = feed_cover_large_video + 1
    private fun getFollowItemType(item: ItemModel<Any>?): Int {
        val data = item?.data
        return when (data) {
            is Metro -> {
                if (data.style.tplLabel == EyepetizerService2.MetroType.Style.feed_cover_large_video) feed_cover_large_video else feed_cover_small_video
            }
            is List<*> -> slide_cover_image_with_footer
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
        item: ItemModel<Any>?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            is ItemVideoCardBinding -> MetroLargeVideoCardItemViewModel(item?.data as Metro)
            is ItemVideoSmallCardBinding -> MetroSmallVideoCardItemViewModel(item?.data as Metro)
            // to be developed
            else -> {
                val data = item?.data
                if (data is Metro) {
                    """
                        ${data.type}
                        ${data.style?.tplLabel}
                        ${data.metroData?.title ?: "null"}
                    """.trimIndent()
                } else {
                    "unknown"
                }
            }
        }
    }

}