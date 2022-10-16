package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.databinding.ItemFollowCardBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_home.api.bean.FollowBean.Item
import com.pp.module_home.model.FollowItemViewModel

class FollowPagingDataAdapter : BindingPagingDataAdapter<ViewDataBinding, Any, Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getFollowItemType(item)
    }

    private fun getFollowItemType(item: Item?): Int {
        return when (item?.type) {
            // FollowCard ==>> 根据 item.data.dataType 判断类型
            else ->
                EyepetizerService.ItemType.getItemType(item?.type ?: "unknown")
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: Item?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            // autoPlayFollowCard,followCard
            is ItemFollowCardBinding ->
                FollowItemViewModel(item,binding.root.context)
            // to be developed
            else -> """
                        ${item?.type}
                        ${item?.data?.dataType}
                        ${item?.data?.content?.data?.title ?: "null"}
            """.trimIndent()
//            else -> FollowItemViewModel(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            // autoPlayFollowCard,followCard
            EyepetizerService.ItemType.AUTO_PLAY_FOLLO_WCARD ,
            EyepetizerService.ItemType.FOLLOW_CARD ->
                ItemFollowCardBinding.inflate(layoutInflater, parent, false)
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
//        return ItemFollowBinding.inflate(LayoutInflater.from(parent.context))
    }

}