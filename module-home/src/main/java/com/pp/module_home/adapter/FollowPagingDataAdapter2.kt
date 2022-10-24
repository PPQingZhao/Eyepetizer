package com.pp.module_home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingPagingDataAdapter
import com.pp.library_network.eyepetizer.bean.PageDataBean.Card.CardData.Body.Metro
import com.pp.library_network.utils.PageType
import com.pp.library_ui.databinding.ItemFollowCardBinding
import com.pp.library_ui.databinding.ItemToBeDevelopedBinding
import com.pp.module_home.model.MetroFollowItemViewModel

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

    val type_video = 1
    private fun getFollowItemType(item: Metro?): Int {
        return when (item?.type) {
            PageType.MetroType.VIDEO -> type_video
            else -> 0
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return when (viewType) {
            type_video -> ItemFollowCardBinding.inflate(layoutInflater, parent, false)
            else -> ItemToBeDevelopedBinding.inflate(layoutInflater, parent, false)
        }
//        return ItemFollowBinding.inflate(LayoutInflater.from(parent.context))
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: Metro?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            // autoPlayFollowCard,followCard
            is ItemFollowCardBinding ->
                MetroFollowItemViewModel(item, binding.root.context)
            // to be developed
            else -> """
                        ${item?.type}
                        ${item?.style?.tplLabel}
                        ${item?.metroData?.title ?: "null"}
            """.trimIndent()
//            else -> FollowItemViewModel(item)
        }
    }


}