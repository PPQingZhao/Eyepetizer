package com.pp.module_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.module_home.api.bean.FollowBean.Item
import com.pp.module_home.databinding.ItemFollowBinding
import com.pp.module_home.model.FollowItemViewModel

class FollowAdapter : BindingAdapter<ViewDataBinding, Any, Item>(DIFF_CALLBACK) {

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
        return if ("textCard" == item?.type) 0 else 0
    }

    override fun createViewModel(
        binding: ViewDataBinding,
        item: Item?,
        cacheItemViewModel: Any?
    ): Any {
        return cacheItemViewModel ?: when (binding) {
            else -> FollowItemViewModel(item)
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemFollowBinding.inflate(LayoutInflater.from(parent.context))
    }

}