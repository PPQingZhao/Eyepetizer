package com.pp.module_community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.module_community.api.bean.CommunityRecBean
import com.pp.module_community.databinding.ItemCommunityRecBinding
import com.pp.module_community.model.RecItemViewModel

class RecAdapter : BindingAdapter<ViewDataBinding, Any, CommunityRecBean.Item>(DIFF_CALLBACK) {

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
            else -> RecItemViewModel(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getRecItemType(item)
    }

    private fun getRecItemType(item: CommunityRecBean.Item?): Int {
        return if ("textCard" == item?.type) 0 else 0
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemCommunityRecBinding.inflate(LayoutInflater.from(parent.context))
    }
}