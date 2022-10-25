package com.pp.module_community.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_network.eyepetizer.EyepetizerService
import com.pp.library_ui.databinding.ItemFollowCardBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_community.api.bean.CommunityRecBean
import com.pp.module_community.databinding.ItemSquareVp2Binding
import com.pp.module_community.model.RecItemViewModel
import com.pp.module_community.model.RecSquareItemViewModel

class RecPagingDataAdapter : BindingPagingDataAdapter<ViewDataBinding, Any, CommunityRecBean.Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "RecAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommunityRecBean.Item>() {

            override fun areItemsTheSame(
                oldItem: CommunityRecBean.Item,
                newItem: CommunityRecBean.Item
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CommunityRecBean.Item,
                newItem: CommunityRecBean.Item
            ) = oldItem == newItem
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: CommunityRecBean.Item?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            is ItemSquareVp2Binding -> RecSquareItemViewModel(item)
            else -> RecItemViewModel(item, binding.root.context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getRecItemType(item)
    }

    private fun getRecItemType(item: CommunityRecBean.Item?): Int {

        val itemType = when (item?.type) {
            // FollowCard ==>> 根据 item.data.dataType 判断类型
            else -> {
                val type = EyepetizerService.ItemType.getItemType(item?.type ?: "unknown")
                type
            }

        }
        Log.e(TAG, "recItemType: ${item?.type}   $itemType")
        return itemType
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        Log.e(TAG, "viewType: ${viewType}")
        return when (viewType) {
            EyepetizerService.ItemType.HORIZONTAL_SCROLL_CARD ->
                ItemSquareVp2Binding.inflate(layoutInflater, parent, false)
            EyepetizerService.ItemType.COMMUNITY_COLUMN_CARD -> {
                val binding = ItemFollowCardBinding.inflate(layoutInflater, parent, false)
                binding.recyclerview.layoutManager = LinearLayoutManager(parent.context)
                binding
            }
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }
}