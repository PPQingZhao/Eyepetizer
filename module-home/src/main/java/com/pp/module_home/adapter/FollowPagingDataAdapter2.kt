package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import com.pp.library_ui.databinding.ItemFollowCardBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.library_common.model.MetroFollowItemViewModel
import com.pp.library_network.eyepetizer.EyepetizerService2

class FollowPagingDataAdapter2 :
    BindingPagingDataAdapter<ViewDataBinding, Any, Metro>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Metro>() {

            override fun areItemsTheSame(oldItem: Metro, newItem: Metro) =
                oldItem.metroId == newItem.metroId

            override fun areContentsTheSame(oldItem: Metro, newItem: Metro) = oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getFollowItemType(item)
    }

    private val item_type_video = 1
    private fun getFollowItemType(item: Metro?): Int {
        return when (item?.type) {
            EyepetizerService2.MetroType.VIDEO -> item_type_video
            else -> 0
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            item_type_video -> {
                val binding = ItemFollowCardBinding.inflate(layoutInflater, parent, false)
                binding.recyclerview.layoutManager = LinearLayoutManager(parent.context)
                binding
            }
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: Metro?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            is ItemFollowCardBinding -> MetroFollowItemViewModel(item)
            // to be developed
            else -> """
                        ${item?.type}
                        ${item?.style?.tplLabel}
                        ${item?.metroData?.title ?: "null"}
            """.trimIndent()
        }
    }


}