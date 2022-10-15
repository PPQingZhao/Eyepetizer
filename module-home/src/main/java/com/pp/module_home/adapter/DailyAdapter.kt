package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_home.api.bean.FeedBean
import com.pp.module_home.databinding.ItemFollowCardBinding
import com.pp.module_home.databinding.ItemHeader5Binding
import com.pp.module_home.model.DailyItemViewModel

class DailyAdapter : BindingAdapter<ViewDataBinding, Any, FeedBean.Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "DailyAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeedBean.Item>() {

            override fun areItemsTheSame(oldItem: FeedBean.Item, newItem: FeedBean.Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FeedBean.Item, newItem: FeedBean.Item) =
                oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getDailyItemType(item)
    }

    private fun getDailyItemType(item: FeedBean.Item?): Int {
        return when (item?.type) {
            // textCard ==>> 根据 item.data.type 判断类型
            EyepetizerService.ItemType.textCard ->
                EyepetizerService.ItemType.getItemType(item.data.type)

            // follow 等 ==>> 根据 item.type 判断类型
            else ->
                EyepetizerService.ItemType.getItemType(item?.type ?: "unknown")
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: FeedBean.Item?,
        cacheItemViewModel: Any?
    ): Any {

        return cacheItemViewModel ?: when (binding) {
            // dataType = followCard  等
            is ItemFollowCardBinding -> DailyItemViewModel(item)
            // type = header5
            is ItemHeader5Binding -> item?.data?.text ?: ""
            // 待开放功能 unknown等
            else -> """
                  ${item?.type}
                  ${item?.data?.content?.data?.title ?: ""}"
            """.trimIndent()
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            // type = followCard 等
            EyepetizerService.ItemType.FOLLOW_CARD ->
                ItemFollowCardBinding.inflate(layoutInflater, parent, false)

            // type = header5
            EyepetizerService.ItemType.HEADER_5 ->
                ItemHeader5Binding.inflate(layoutInflater, parent, false)
            // 待开放功能
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

}