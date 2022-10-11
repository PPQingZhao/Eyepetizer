package com.pp.module_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pp.library_base.adapter.BindingAdapter
import com.pp.library_network.eyepetizer.bean.follow.Item
import com.pp.module_home.R
import com.pp.module_home.model.FollowItemViewModel

class FollowAdapter : BindingAdapter<Any, Item>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "FollowAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
        }
    }

    override fun createItemViewModel(
        itemViewType: Int,
        item: Item?,
        cacheItemViewModel: Any?
    ): Any? {
        return cacheItemViewModel ?: FollowItemViewModel(item)
    }

    override fun createItemView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getFollowItemType(item)
    }

    private fun getFollowItemType(item: Item?): Int {
        return if ("textCard" == item?.type) 0 else 0
    }

}